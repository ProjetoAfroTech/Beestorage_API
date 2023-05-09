package com.beestorage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_produto")
public class Produto {

		@Id
		@Column(name="id_produto")
		private Long idProduto;
		
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
