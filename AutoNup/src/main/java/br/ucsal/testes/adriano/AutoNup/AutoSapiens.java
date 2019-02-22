package br.ucsal.testes.adriano.AutoNup;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.jar.asm.commons.TryCatchBlockSorter;

public class AutoSapiens
{

	public static void main(String[] args) throws AWTException, InterruptedException
	{
		WebDriver driver;
		WebElement element;
		

		WebElement senha;

		WebElement button;
		File file = new File("Lista.txt");
    	String nome = ""; 
    	String conteudo;
		String test = null;
		ArrayList<String> NUP = new ArrayList<String>();
		ArrayList<String> resposta = new ArrayList<String>();
	 String loginuser, pass;

	 loginuser=JOptionPane.showInputDialog(null, "insira o usuário: ", "PESQUISA AUTOMÁTICA SEIN", JOptionPane.DEFAULT_OPTION);
	pass =JOptionPane.showInputDialog(null, "insira seu passowrd: ", "PESQUISA AUTOMÁTICA SEIN", JOptionPane.DEFAULT_OPTION);
	try {
    FileReader arq = new FileReader("Lista.txt");
    BufferedReader lerArq = new BufferedReader(arq);

    String linha = lerArq.readLine(); 
    NUP.add(linha);

    while (linha != null) {
      System.out.printf("%s\n", linha);

      linha = lerArq.readLine(); 
      NUP.add(linha);
    }

    arq.close();
  } catch (IOException e) {
      System.err.printf("Erro na abertura do arquivo: %s.\n",
        e.getMessage());
  }
	
	System.out.println(NUP.size());
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		
		ChromeOptions chrome = new ChromeOptions();
		chrome.setHeadless(false);
		driver = new ChromeDriver(chrome);

		driver.get("https://sapiens.agu.gov.br/login");
		element = driver.findElement(By.name("username"));
		senha = driver.findElement(By.name("password"));

		element.sendKeys(loginuser);
		senha.sendKeys(pass);
		driver.findElement(By.id("button-1019-btnInnerEl")).click();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("splitbutton-1011")));
		button = driver.findElement(By.id("splitbutton-1011"));
		Actions act = new Actions(driver);
		Thread.sleep(8000);
		try
		{
			
		
		//aqui inicia o processo de pesquisa
		for (int i = 0; i < NUP.size(); i++)
		{//Thread.sleep(5000);
	//"displayfield-1417-inputEl"
	//driver.findElement(By.id("pesquisaNUP-inputEl")).click();
		WebElement talk1, element3;
		String adress ="pesquisaNUP-inputEl";

		element3 = driver.findElement(By.id(adress));
		element3.clear();
		element3.sendKeys(NUP.get(i));
		Thread.sleep(5000);
		element3.sendKeys(Keys.ENTER);
//	talk1 = driver.findElement(By.id(adress));
	//talk1.click();
	//talk1.sendKeys(NUP.get(0));
	//talk1.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	test = driver.findElement(By.id("displayfield-1417-inputEl")).getText();
		resposta.add(test);


			}
		} catch (Exception e)
		{
		System.out.println("Não foi possível pesquisar os NUPs");	// TODO: handle exception
		} 
			// Aqui termina a pesquisa
		 try	{
FileWriter arq2 = new FileWriter("Pesquisa.txt");
	           
	            
	            for (int i=0; i<resposta.size();i++)
	    		{
	    		
	    		
	            
	           
	    conteudo = resposta.get(i);
	    conteudo += "\r\n";
	    arq2.write(conteudo);
	    		}
	            	
	            
	            arq2.close();
	        }catch (IOException e)  {
	                e.printStackTrace();
	        }
			
			
JOptionPane.showMessageDialog(null, "A Pesquisa foi concluída, favor verificar o arquivo pesquisa.txt", "PESQUISA AUTOMÁTICA SEIN", JOptionPane.WARNING_MESSAGE);
	}
		
}

