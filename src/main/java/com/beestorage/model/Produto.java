package com.beestorage.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_produtos")
public class Produto {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long idProduto;
		
		@NotBlank(message = "O nome do produto é obrigatório!") 
		@Size(min = 5, max = 50, message = "O nome deve ter no minimo 05 e no máximo 50 caracteres")
		private String nomeProduto;
		
		@NotBlank
		private Long quantidadeProduto;
		
		@NotBlank
		private Long estoqueMinimoProduto;
		
		@NotBlank
		private Long valorProduto;
		
		//@OneToMany
		//@JsonIgnoreProperties("produto")
		//private Categoria categoria;
		
		public String getNomeProduto() {
			return nomeProduto;
		}

		public void setNomeProduto(String nomeProduto) {
			this.nomeProduto = nomeProduto;
		}

		public Long getQuantidadeProduto() {
			return quantidadeProduto;
		}

		public void setQuantidadeProduto(Long quantidadeProduto) {
			this.quantidadeProduto = quantidadeProduto;
		}

		public Long getEstoqueMinimoProduto() {
			return estoqueMinimoProduto;
		}

		public void setEstoqueMinimoProduto(Long estoqueMinimoProduto) {
			this.estoqueMinimoProduto = estoqueMinimoProduto;
		}

		public Long getValorProduto() {
			return valorProduto;
		}

		public void setValorProduto(Long valorProduto) {
			this.valorProduto = valorProduto;
		}

		public Produto(){
			super();
		}

		public Long getIdProduto() {
			return idProduto;
		}

		public void setIdProduto(Long idProduto) {
			this.idProduto = idProduto;
		}
}
