package com.biblioteca.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dtos.CategoriaDto;
import com.biblioteca.dtos.CategoriaSimplesDto;
import com.biblioteca.dtos.LivroSimplesDto;
import com.biblioteca.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {
	private final CategoriaService service;

	@Autowired // injetando o CategoriaService
	public CategoriaController(CategoriaService categoriaService) {
		this.service = categoriaService;
	}

	@PostMapping // mapeia requisições POST para "/categorias"
	public ResponseEntity<CategoriaDto> salvarCategoria(@RequestBody CategoriaDto categoriaDto) { // @RequestBody //
																									// objeto Categoria.
		// Chama o serviço para salvar a nova categoria
		CategoriaDto novaCategoriaDto = service.salvarCategoria(categoriaDto);
		// Cria a URI para o novo recurso
		URI location = URI.create("/categorias/" + novaCategoriaDto.getId());
		// Retorna 201 (Created) com a localização do novo recurso
		return ResponseEntity.created(location).body(novaCategoriaDto);
	}

	@GetMapping // Mapeia requisições GET para o endpoint /categorias.
	public ResponseEntity<List<CategoriaSimplesDto>> listarCategorias() {
		List<CategoriaSimplesDto> categorias = service.listarCategorias();

		// retorna 204 (No Content) se não houver categorias
		// e 200 (OK) com a lista de categorias se houver.
		return categorias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscarCategoriaPorId(@PathVariable("id") Long id) {
		CategoriaDto categoriaDto = service.buscarCategoriaPorId(id);

		return ResponseEntity.ok(categoriaDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable("id") Long id,
			@RequestBody CategoriaDto categoriaDto) {
		// Atualiza a categoria
		CategoriaDto categoriaAtualizada = service.atualizarCategoria(id, categoriaDto);
		// Retorna 200 OK com a categoria atualizada
		return ResponseEntity.ok(categoriaAtualizada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerCategoria(@PathVariable("id") Long id) {
		// Chama o serviço para remover a categoria
		service.removerCategoria(id);

		// Retorna 204 (No Content) se a remoção for bem-sucedida
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/lista/{nome}")
	public ResponseEntity<List<CategoriaSimplesDto>> listarCategoriasPorNome(@PathVariable("nome") String nome) {
	    List<CategoriaSimplesDto> categorias = service.listarCategoriasPorNome(nome);
	    return categorias.isEmpty() ? ResponseEntity.noContent().build() : 
	    	ResponseEntity.ok(categorias);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<CategoriaDto> buscarCategoriaPorNome(@PathVariable("nome") String nome) {
		CategoriaDto categoriaDto = service.buscarCategoriaPorNome(nome);

		return categoriaDto != null ? ResponseEntity.ok(categoriaDto) : ResponseEntity.notFound().build();
	}

	@GetMapping("/{nome}/livros")
	public ResponseEntity<List<LivroSimplesDto>> listarLivrosPorCategoria(@PathVariable("nome") String nome) {
		List<LivroSimplesDto> livros = service.listarLivrosPorNomeCategoria(nome);
		return livros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(livros);
	}
}
