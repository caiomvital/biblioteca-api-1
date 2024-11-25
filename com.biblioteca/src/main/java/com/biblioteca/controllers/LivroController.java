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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dtos.LivroDto;
import com.biblioteca.dtos.LivroSimplesDto;
import com.biblioteca.service.LivroService;

@RestController
@RequestMapping("/livros")
@CrossOrigin("*")
public class LivroController {

	private final LivroService livroService;

	@Autowired
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@PostMapping
	public ResponseEntity<LivroDto> salvarLivro(@RequestParam(value = "categoria") String nomeCategoria,
			@RequestBody LivroDto livroDto) {

		// Chama o serviço para salvar o novo livro
		LivroDto novoLivro = livroService.salvarLivro(nomeCategoria, livroDto);

		// Cria a URI para o novo recurso
		URI location = URI.create("/livros/" + novoLivro.getId());

		// Retorna 201 (Created) com a localização do novo recurso
		return ResponseEntity.created(location).body(novoLivro);
	}

	// Método para listar todos os livros
	@GetMapping
    public ResponseEntity<List<LivroSimplesDto>> listarLivros() {
        List<LivroSimplesDto> livros = livroService.listarLivros();
        return livros.isEmpty() ? ResponseEntity.noContent().build() : 
        	ResponseEntity.ok(livros);
    }

	// Método para buscar um livro pelo ID
	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> buscarLivroPorId(@PathVariable("id") Long id) {
		LivroDto livroDto = livroService.buscarLivroPorId(id);
		return ResponseEntity.ok(livroDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LivroDto> atualizarLivro(@PathVariable("id") Long id, @RequestBody LivroDto livroDto) {
		LivroDto livroAtualizado = livroService.atualizarLivro(id, livroDto);
		return ResponseEntity.ok(livroAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerLivro(@PathVariable("id") Long id) {
		livroService.removerLivro(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/categoria/{nome}/livros")
    public ResponseEntity<List<LivroSimplesDto>> listarLivrosPorCategoria(@PathVariable("nome") String nome) {
        List<LivroSimplesDto> livros = livroService.listarLivrosPorCategoria(nome);
        return livros.isEmpty() ? ResponseEntity.noContent().build() : 
        	ResponseEntity.ok(livros);
    }

}
