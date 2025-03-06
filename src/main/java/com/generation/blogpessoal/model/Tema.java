package com.generation.blogpessoal.model;

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
import jakarta.validation.constraints.NotNull;

@Entity // indicação que isso é uma nova tabela do banco de dados
@Table(name = "tb_temas") // indicar o nome dessa tabela no banco de dados
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo Descrição é obrigatório")
	private String descricao;
	
	/*
	 * Tema => one / Postagem => Many
	 * fetch = Lazy pesquisa preguiçosa, traz somento o que foi solicitado - EAGER traz todas as informações
	 * Cascade => como vai se comportar a tabela relacionada em momento de deletar dados
	 * CascadeType.Remove quando um Objeto da Classe Tema for apagado, todos os Objetos da Classe
	 * Postagem associoados ao Tema apagado, também serão apagados. O inverso não é verdadeiro.
	 */
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tema", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;
	//tornamos a postagem lista pq podemos ter mais de uma postagem para o mesmo tema
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Postagem> getPostagem() {
		return postagem;
	}
	
	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}		

}
