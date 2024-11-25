package com.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dtos.CategoriaDto;
import com.biblioteca.dtos.CategoriaSimplesDto;
import com.biblioteca.dtos.LivroSimplesDto;
import com.biblioteca.exceptions.CategoriaEmUsoException;
import com.biblioteca.exceptions.CategoriaExistenteException;
import com.biblioteca.exceptions.CategoriaNaoEncontradaException;
import com.biblioteca.models.Categoria;
import com.biblioteca.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

	private final CategoriaRepository repository;
	private final ModelMapper mapper;

// precisamos de um atributo ModelMapper nas classes 
// em que será usado.

	@Autowired // injeção do repositório através do construtor
	public CategoriaService(CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
		this.repository = categoriaRepository;
		this.mapper = modelMapper;

	}

	@Transactional
	public CategoriaDto salvarCategoria(CategoriaDto categoriaDto) {
		if (repository.existsByNomeIgnoreCase(categoriaDto.getNome())) {
			throw new CategoriaExistenteException(categoriaDto.getNome());
		}
		Categoria categoria = mapper.map(categoriaDto, Categoria.class);
		Categoria categoriaSalva = repository.save(categoria);
		return mapper.map(categoriaSalva, CategoriaDto.class);
	}

	// Método para listar todas as categorias e a quantidade de livros em cada uma
	public List<CategoriaSimplesDto> listarCategorias() {
		return repository.findAll().stream().map(categoria -> {
			CategoriaSimplesDto dto = new CategoriaSimplesDto(categoria.getId(), categoria.getNome(),
					categoria.getLivros() != null ? categoria.getLivros().size() : 0);

			return dto;
		}).collect(Collectors.toList());
	}

	// Método para buscar uma categoria pelo id
	public CategoriaDto buscarCategoriaPorId(Long id) {
		return repository.findById(id).map(categoria -> mapper.map(categoria, CategoriaDto.class))
				.orElseThrow(() -> new CategoriaNaoEncontradaException(id));
	}

	@Transactional
	public CategoriaDto atualizarCategoria(Long id, CategoriaDto categoriaDto) {
		return repository.findById(id).map(categoriaExistente -> {
			String nomeAtualizado = categoriaDto.getNome();

			// Verifica se o nome da categoria já existe e não é o mesmo da atual
			if (!categoriaExistente.getNome().equalsIgnoreCase(nomeAtualizado)
					&& repository.existsByNomeIgnoreCase(nomeAtualizado)) {
				throw new CategoriaExistenteException(nomeAtualizado);
			}

			// Atualiza o nome da categoria existente
			categoriaExistente.setNome(nomeAtualizado);
			// Aqui é possível atualizar outros campos, se necessário

			// Salva a categoria atualizada
			Categoria categoriaAtualizada = repository.save(categoriaExistente);
			return mapper.map(categoriaAtualizada, CategoriaDto.class);
		}).orElseThrow(() -> new CategoriaNaoEncontradaException(id));
	}

	@Transactional
	public void removerCategoria(Long id) {
		// Verifica se a categoria existe
		Categoria categoria = repository.findById(id)
				.orElseThrow(() -> new CategoriaNaoEncontradaException(id));

		// Verifica se a categoria contém livros
		if (repository.existsByIdAndLivrosIsNotEmpty(id)) {
			// aqui é utilizado um método personalizado criado no repositório
			throw new CategoriaEmUsoException(categoria.getNome());
		}

		// Remove a categoria
		repository.delete(categoria);
	}

	public List<CategoriaSimplesDto> listarCategoriasPorNome(String nome) {
		return repository.findAllByNomeContainingIgnoreCase(nome).stream()
				.map(categoria -> mapper.map(categoria, CategoriaSimplesDto.class)).collect(Collectors.toList());
	}

	public CategoriaDto buscarCategoriaPorNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome).map(categoria -> mapper.map(categoria, CategoriaDto.class))
				.orElseThrow(() -> new CategoriaNaoEncontradaException(nome));
	}

	public List<LivroSimplesDto> listarLivrosPorNomeCategoria(String nome) {
		CategoriaDto categoria = buscarCategoriaPorNome(nome);

		return categoria.getLivros().stream()
				.map(livro -> new LivroSimplesDto(livro.getId(), livro.getTitulo(), livro.getAutor()))
				.collect(Collectors.toList());
	}

}