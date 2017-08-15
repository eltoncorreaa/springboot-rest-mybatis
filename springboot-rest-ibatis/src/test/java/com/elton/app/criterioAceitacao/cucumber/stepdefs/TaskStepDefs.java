package com.elton.app.criterioAceitacao.cucumber.stepdefs;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.elton.app.dto.TaskDTO;
import com.elton.app.service.TaskService;
import com.elton.app.utils.AbstractDefs;
import com.elton.app.utils.factory.TaskMother;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class TaskStepDefs extends AbstractDefs {

	@Autowired
	private TaskService taskService;

	private TaskDTO DTO = new TaskDTO();

	private static Map<String, String> mapaCampos = new HashMap<String, String>();

	static {
		mapaCampos.put("Nome", "setName");
	}

	@Override
	public Map<String, String> getMapaCampos() {
		return mapaCampos;
	}

	@Dado("^uma Task em registro$")
	public void uma_Task_em_registro() throws Throwable {
		DTO = TaskMother.getTaskDTOPadrao();
	}

	@Quando("^informo o nome da Task \"([^\"]*)\"$")
	public void informo_o_nome_da_task(final String novoNome) throws Throwable {
		DTO.setName(novoNome);
	}

	@Quando("^tento salvar a Task$")
	public void tento_salvar_a_Task() throws Throwable {
		try {
			DTO = taskService.persist(DTO);
		} catch (final RuntimeException re) {
			tratarErrosDeExcecao(re);
		}
	}

	@Entao("^A Task foi incluída com sucesso.$")
	public void A_task_foi_incluída_com_sucesso() throws Throwable {
		Assert.assertNotNull(DTO.getId());
	}

	@Quando("^a informação obrigatória (.*) da task com o valor \"([^\"]*)\"$")
	public void tento_incluir_uma_Task_com_a_informacao_obrigatoria_igual_a(final String campo, final String valor)
			throws Throwable {
		preencheCampo(DTO, campo, valor);
	}

	@Entao("^falha:O preenchimento do nome é obrigatório.$")
	public void O_preenchimento_do_Fator_Influenciador_e_obrigatorio() throws Throwable {
		Assert.assertTrue(verificaRetornoExcecao("O Nome é obrigatório."));
	}


}
