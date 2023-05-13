package com.beestorage.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria") 
public class CategoriaModel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@NotBlank (message = "O atributo categorias é obrigatório e não pode utilizar espaços em branco!") 
	@Size(min = 5, max = 20, message = "O atributo categorias deve conter no mínimo 05 e no máximo 20 caracteres")
	private String nomeCategoria;
	
	@NotBlank (message = "O atributo categorias é obrigatório e não pode utilizar espaços em branco!") 
	@Size(min = 5, max = 20, message = "O atributo categorias deve conter no mínimo 05 e no máximo 20 caracteres")
	private String subCategoria;
	
	@NotNull
	private int volumes;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("categoria")
	private List <ProdutoModel> produto;
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public int getVolumes() {
		return volumes;
	}

	public void setVolumes(int volumes) {
		this.volumes = volumes;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
}
