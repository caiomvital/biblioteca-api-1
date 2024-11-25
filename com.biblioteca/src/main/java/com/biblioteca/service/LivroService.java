package com.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.biblioteca.dtos.CategoriaDto;
import com.biblioteca.dtos.LivroDto;
import com.biblioteca.dtos.LivroSimplesDto;
import com.biblioteca.exceptions.LivroExistenteException;
import com.biblioteca.exceptions.LivroNaoEncontradoException;
import com.biblioteca.models.Categoria;
import com.biblioteca.models.Livro;
import com.biblioteca.repositories.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class LivroService {

	private final LivroRepository repository;
	private final CategoriaService categoriaService;
	private final ModelMapper mapper;

	public LivroService(LivroRepository livroRepository, CategoriaService categoriaService, ModelMapper mapper) {
		this.repository = livroRepository;
		this.categoriaService = categoriaService;
		this.mapper = mapper;
	}

	// o método recebe um nome de categoria para adicionar o livro
	@Transactional
	public LivroDto salvarLivro(String nomeCategoria, LivroDto livroDto) {
		Livro livro = mapper.map(livroDto, Livro.class);
		CategoriaDto categoriaDto = categoriaService.buscarCategoriaPorNome(nomeCategoria);
		Categoria categoria = mapper.map(categoriaDto, Categoria.class);
		livro.setCategoria(categoria);

		if (repository.existsByTituloAndAutorIgnoreCase(livro.getTitulo(), livro.getAutor())) {
			throw new LivroExistenteException(livro.getTitulo(), livro.getAutor());
		}

		Livro livroSalvo = repository.save(livro);
		return mapper.map(livroSalvo, LivroDto.class);

	}

	// Método para buscar um livro por ID
	public LivroDto buscarLivroPorId(Long id) {
		return repository.findById(id).map(livro -> mapper.map(livro, LivroDto.class))
				.orElseThrow(() -> new LivroNaoEncontradoException(id));
	}

	// Método para listar os livros
	public List<LivroSimplesDto> listarLivros() {
		return repository.findAll().stream()
				.map(livro -> new LivroSimplesDto(livro.getId(), livro.getTitulo(), livro.getAutor()))
				.collect(Collectors.toList());
	}

	@Transactional
	public LivroDto atualizarLivro(Long id, LivroDto livroAtualizado) {
		// Busca o livro existente
		Livro livroExistente = repository.findById(id)
				.orElseThrow(() -> new LivroNaoEncontradoException(id));
		// Busca a categoria pelo nome
		CategoriaDto categoriaDto = categoriaService.buscarCategoriaPorNome(livroAtualizado.getCategoria().getNome());
		Categoria categoria = mapper.map(categoriaDto, Categoria.class);
		// Atualiza os dados do livro
		livroExistente.setTitulo(livroAtualizado.getTitulo());
		livroExistente.setAutor(livroAtualizado.getAutor());
		livroExistente.setCategoria(categoria);
		// Salva as alterações
		Livro livroSalvo = repository.save(livroExistente);

		// Converte para DTO e retorna
		return mapper.map(livroSalvo, LivroDto.class);

		/*
		 * Este método:
		 * 
		 * Recebe dois parâmetros: id do Livro original e o novo Livro Faz duas
		 * validações importantes: a. Verifica se o livro que queremos atualizar existe.
		 * b. Verifica se a nova categoria informada existe pelo nome. 3. Se os dois
		 * forem válidos, atualiza os dados do livro existente. 4. Salva as alterações
		 * feitas. 5. Converte o livro atualizado para um objeto LivroDto e o retorna.
		 */
	}

	@Transactional
	public void removerLivro(Long id) {
		// Verifica se o livro existe
		Livro livroExistente = repository.findById(id)
				.orElseThrow(() -> new LivroNaoEncontradoException(id));

		// Remove o livro do banco de dados
		repository.delete(livroExistente);
	}

	// Método para buscar todos os livros por nome da categoria
	public List<LivroSimplesDto> listarLivrosPorCategoria(String nome) {
		return categoriaService.listarLivrosPorNomeCategoria(nome);

	}

}