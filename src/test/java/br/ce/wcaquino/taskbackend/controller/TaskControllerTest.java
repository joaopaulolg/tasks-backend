package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		try {
			Task task = new Task();
			task.setDueDate(LocalDate.now());
			this.controller.save(task);
			Assert.fail("Não deve chegar até aqui!");
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		try {
			Task task = new Task();
			task.setTask("Task 1");
			this.controller.save(task);
			Assert.fail("Não deve chegar até aqui!");
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the due date", e.getMessage());
		}
	}

	@Test
	public void naoDeveSalvarTarefaComDataPassadaAtual() {
		try {
			Task task = new Task();
			task.setTask("Task 1");
			task.setDueDate(LocalDate.of(2010, 1, 1));
			this.controller.save(task);
			Assert.fail("Não deve chegar até aqui!");
		} catch (ValidationException e) {
			Assert.assertEquals("Due date must not be in past", e.getMessage());
		}
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException {
		Task task = new Task();
		task.setTask("Task 1");
		task.setDueDate(LocalDate.now());
		this.controller.save(task);
		Mockito.verify(taskRepo).save(task);
	}
}
