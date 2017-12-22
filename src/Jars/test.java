package Jars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test {
	
		final static String email="narkozetkisii@gmail.com"; // Sadece oyunlarda kullan�yorum :)
		final static String password="s�f�rbiriki��"; 
		final static String word="samsung";  // Aranacak kelime
		final static WebDriver driver = new FirefoxDriver(); // Hata vermemesi i�in geckodriver'i windows path'a eklemem gerekti yoksa hata al�yordum, 
		  													 //Farkl� bir taray�c� i�in bu ayarlar� tekrar yapmak gerekiyor ben mozilla kullan�yorum.
														     //Main d���ndaki metodlar�n ula�abilmesi i�in burda tan�ml�yorum.
	
		public static void main(String[] args) {
			
		test object = new test();   // Metodlar� �a��rabilmek i�in bir nesne 
		
		driver.get("https://www.amazon.com/");   
		System.out.println("Mozilla Firefox a��ld�, amazon.com'a girildi");
		
		driver.manage().window().maximize();    
		System.out.println("Full ekran oldu");
				
		WebElement signbuton = driver.findElement(By.id("nav-signin-tooltip")); //Sign butonu tan�mlan�yor
		signbuton.click();          
		System.out.println("Giri� Butonuna t�kland�");
		
		object.login();			// Giri� i�lemi yap�l�yor	
		
		object.searchSamsung(); // Aramaya samsung yaz�p aran�yor
		
		object.findResult();  // Sonuc bulunup bulunmad�g�n� onayl�yor
		
		object.secondPage(); // 2. Sayfaya ge�iyor
		
		object.clickThirdProduct(); //3. �r�ne t�kl�yor
		
	 			
		WebElement product = driver.findElement(By.xpath(".//*[@id='productTitle']")); // �r�n�n ismini kaydediyor daha sonra kullan�lacak
		String productname = product.getText();
		System.out.println("�r�n�n ad� : "+ productname);
		
		WebElement addtolist = driver.findElement(By.xpath(".//*[@id='add-to-wishlist-button-submit']"));
		addtolist.click();
		System.out.println("Add liste t�kland�");
		
		object.getWishList(); // wish listi a�ar

		
		String b = "//*[contains(text(), '"+productname+"')]"; //Se�ilen �r�n�n xpath�n� sayfada bulmak i�in daha �nce urunun ad�n� tuttugum productname'i kullan�yorum
		
		object.isProductInList(b); // Add list'e eklenen �r�n�n wish listte olup olmad�g�n� kontrol ediyor

				
		WebElement delete = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div/div/div/div[2]/div[2]/div[3]/div/div/div[1]/div/div/div[2]/div[3]/div/div[2]/div/div/div[1]/div[2]/form/span/span/span/input"));
		delete.click();
		System.out.println("Silme i�lemini tamamlad�");
		
		driver.navigate().refresh();
		System.out.println("Sayfa yenileniyor");
		
		object.isProductInList(b); //Silinen �r�n�n listede olup olmad���na bak�yor

	}
	
	public void login(){
		
		try {
		WebElement form1,form2,login ;
		form1= driver.findElement(By.id("ap_email"));
		form2= driver.findElement(By.id("ap_password"));
		login= driver.findElement(By.id("signInSubmit"));
		form1.sendKeys(email); 
		form2.sendKeys(password);
		login.click();
		System.out.println("Giri� Yap�ld�");
		} catch (Exception e) {
			System.out.println("Giri� Yapmada bir hata olu�tu: "+e);
		}	
		
	}
	
	public void searchSamsung(){
		WebElement searchbar,search;
		searchbar = driver.findElement(By.id("twotabsearchtextbox"));
		search = driver.findElement(By.xpath(".//*[@id='nav-search']/form/div[2]/div/input"));
		searchbar.sendKeys(word);
		search.click();
		System.out.println("Aramaya samsung yaz�l�p araya t�kland�");
	}
	
	public void findResult(){
		WebElement result;  // �r�n bulunup bulunmad���n� belirten textleri atamak i�in tan�mland�
		
		try {
			result = driver.findElement(By.xpath(".//*[@id='s-result-count']"));  //  Aranan �r�n var
			System.out.println("�r�n buldundu : " + result.getText());
		} catch (Exception e) {
			result = driver.findElement(By.xpath(".//*[@id='noResultsTitle']")); // Aranan �r�n yok
			System.out.println("Aranan �r�n yok : " + result.getText());
			driver.quit();
			driver.close();
		}
	}
	
	public void secondPage(){
		WebElement secondpage;
		try {
			secondpage = driver.findElement(By.xpath(".//*[@id='pagn']/span[3]/a"));
			secondpage.click();
			System.out.println("2. sayfaya ge�ildi");
		} catch (Exception e) {
			System.out.println("2. sayfa yok");
			driver.quit();
			driver.close();
		}
		
	}
	
	public void clickThirdProduct(){
		WebElement thirdproduct;
		
		thirdproduct= driver.findElement(By.xpath(".//*[@id='result_18']/div/div/div/div[1]/div/div/a/img"));
		thirdproduct.click();
		
		thirdproduct= driver.findElement(By.xpath(".//*[@id='result_18']/div/div/div/div[1]/div/div/a/img"));
		thirdproduct.click(); 
		// 3. �r�ne 1 kere t�klay�nca sayfa yenileniyor galiba bir hata var o yuzden ikinci atamay� yap�p tekrar t�kl�yorum giriyor
		
		System.out.println("3. �r�n se�ildi");
	}
	
	public void getWishList(){
		/* Add Liste t�klay�nca kar��m�za 2 sayfa ��kabiliyor.
		 * Muhtemelen internet h�z�na ve ya taray�c�n�n o anki durumuna bagl� olarak.
		 * Yeni sayfa a��l�rsa 1. y�ntem, Ayn� sayfada i�lem yap�l�rsa 2. y�ntem kullan�l�yor */
		
		WebElement viewlist;
		
		try {                                       
			viewlist = driver.findElement(By.xpath(".//*[@id='WLHUC_viewlist']/span/span"));
			System.out.println(viewlist.getText());
			viewlist.click();		
			System.out.println("Yontem 1 ile ac�ld�");
		} catch (Exception e) {
			driver.navigate().back();
			System.out.println("Yontem 2 ile ac�ld�");
		}
		driver.findElement(By.xpath(".//*[@id='nav-link-accountList']/span[2]")).click(); //account&list'e t�klar
		driver.findElement(By.xpath(".//*[@id='a-page']/div[3]/div/div[4]/div[1]/div/div/ul/li[6]/span/a")).click(); //list'i se�ip t�klar
	}
	
	public void isProductInList(String b){
		
		try {
			WebElement compare=driver.findElement(By.xpath(b)); // Bu atama yap�l�rsa �r�n listede var demektir
			System.out.println("�r�n wish listte var ve ismi : "+compare.getText());
		} catch (Exception e) {
			System.out.println("�r�n Listede Yok");
		}
	}

}
