package com.beestorage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beestorage.model.ProdutoModel;
import com.beestorage.repository.ProdutoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.Optional;




@RestController
@RequestMapping ("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	 
	@Autowired
	private UsuarioRepository usuarioRepository;
	    
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> GetAll(){	
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> GetById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resp ->ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nomeProduto}")
	public ResponseEntity<List<ProdutoModel>> GetByNome(@PathVariable String nomeProduto){
		return ResponseEntity.ok(produtoRepository
				.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
		
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> PostById(@RequestBody ProdutoModel produto) {
	    Optional<CategoriaModel> categoriaOptional = categoriaRepository.findById(produto.getCategoria().getId());
	    Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(produto.getUsuario().getId());
	    
	    if (categoriaOptional.isPresent() && usuarioOptional.isPresent()) {
	        produto.setCategoria(categoriaOptional.get());
	        produto.setUsuario(usuarioOptional.get());
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	    }
	    
	    return ResponseEntity.notFound().build();
	}

	@PutMapping
	public ResponseEntity<ProdutoModel> putPostagem (@Valid @RequestBody ProdutoModel produto){


		if (produtoRepository.existsById(produto.getId())){
			
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produto));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PatchMapping("/{id}/entrada")
	public ResponseEntity<ProdutoModel> adicionarQuantidade(@PathVariable Long id, @RequestBody Map<String, Integer> quantidadeMap) {
	    int quantidade = quantidadeMap.get("quantidade");
	    
	    Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);
	    if (produtoOptional.isPresent()) {
	        ProdutoModel produto = produtoOptional.get();
	        produto.setQtdProduto(produto.getQtdProduto() + quantidade);
	        ProdutoModel produtoAtualizado = produtoRepository.save(produto);
	        return ResponseEntity.ok(produtoAtualizado);
	    }
	    
	    return ResponseEntity.notFound().build();
	}


	@PatchMapping("/{id}/saida")
	public ResponseEntity<ProdutoModel> subtrairQuantidade(@PathVariable Long id, @RequestBody Map<String, Integer> quantidadeMap) {
	    int quantidade = quantidadeMap.get("quantidade");
	    
	    Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);
	    if (produtoOptional.isPresent()) {
	        ProdutoModel produto = produtoOptional.get();
	        int novaQuantidade = produto.getQtdProduto() - quantidade;
	        if (novaQuantidade >= 0) {
	            produto.setQtdProduto(novaQuantidade);
	            ProdutoModel produtoAtualizado = produtoRepository.save(produto);
	            return ResponseEntity.ok(produtoAtualizado);
	        } else {
	            return ResponseEntity.badRequest().body(produto);
	        }
	    }
	    return ResponseEntity.notFound().build();
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);
	    if (produtoOptional.isPresent()) {
	        ProdutoModel produto = produtoOptional.get();
	        produto.setCategoria(null);
	        produto.setUsuario(null);
	        produtoRepository.save(produto);
	        produtoRepository.deleteById(id);
	        
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
		
}
