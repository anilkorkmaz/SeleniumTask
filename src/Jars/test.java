package Jars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test {
	
		final static String email="narkozetkisii@gmail.com"; // Sadece oyunlarda kullanýyorum :)
		final static String password="sýfýrbirikiüç"; 
		final static String word="samsung";  // Aranacak kelime
		final static WebDriver driver = new FirefoxDriver(); // Hata vermemesi için geckodriver'i windows path'a eklemem gerekti yoksa hata alýyordum, 
		  													 //Farklý bir tarayýcý için bu ayarlarý tekrar yapmak gerekiyor ben mozilla kullanýyorum.
														     //Main dýþýndaki metodlarýn ulaþabilmesi için burda tanýmlýyorum.
	
		public static void main(String[] args) {
			
		test object = new test();   // Metodlarý çaðýrabilmek için bir nesne 
		
		driver.get("https://www.amazon.com/");   
		System.out.println("Mozilla Firefox açýldý, amazon.com'a girildi");
		
		driver.manage().window().maximize();    
		System.out.println("Full ekran oldu");
				
		WebElement signbuton = driver.findElement(By.id("nav-signin-tooltip")); //Sign butonu tanýmlanýyor
		signbuton.click();          
		System.out.println("Giriþ Butonuna týklandý");
		
		object.login();			// Giriþ iþlemi yapýlýyor	
		
		object.searchSamsung(); // Aramaya samsung yazýp aranýyor
		
		object.findResult();  // Sonuc bulunup bulunmadýgýný onaylýyor
		
		object.secondPage(); // 2. Sayfaya geçiyor
		
		object.clickThirdProduct(); //3. Ürüne týklýyor
		
	 			
		WebElement product = driver.findElement(By.xpath(".//*[@id='productTitle']")); // Ürünün ismini kaydediyor daha sonra kullanýlacak
		String productname = product.getText();
		System.out.println("Ürünün adý : "+ productname);
		
		WebElement addtolist = driver.findElement(By.xpath(".//*[@id='add-to-wishlist-button-submit']"));
		addtolist.click();
		System.out.println("Add liste týklandý");
		
		object.getWishList(); // wish listi açar

		
		String b = "//*[contains(text(), '"+productname+"')]"; //Seçilen ürünün xpathýný sayfada bulmak için daha önce urunun adýný tuttugum productname'i kullanýyorum
		
		object.isProductInList(b); // Add list'e eklenen ürünün wish listte olup olmadýgýný kontrol ediyor

				
		WebElement delete = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div/div/div/div[2]/div[2]/div[3]/div/div/div[1]/div/div/div[2]/div[3]/div/div[2]/div/div/div[1]/div[2]/form/span/span/span/input"));
		delete.click();
		System.out.println("Silme iþlemini tamamladý");
		
		driver.navigate().refresh();
		System.out.println("Sayfa yenileniyor");
		
		object.isProductInList(b); //Silinen ürünün listede olup olmadýðýna bakýyor

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
		System.out.println("Giriþ Yapýldý");
		} catch (Exception e) {
			System.out.println("Giriþ Yapmada bir hata oluþtu: "+e);
		}	
		
	}
	
	public void searchSamsung(){
		WebElement searchbar,search;
		searchbar = driver.findElement(By.id("twotabsearchtextbox"));
		search = driver.findElement(By.xpath(".//*[@id='nav-search']/form/div[2]/div/input"));
		searchbar.sendKeys(word);
		search.click();
		System.out.println("Aramaya samsung yazýlýp araya týklandý");
	}
	
	public void findResult(){
		WebElement result;  // Ürün bulunup bulunmadýðýný belirten textleri atamak için tanýmlandý
		
		try {
			result = driver.findElement(By.xpath(".//*[@id='s-result-count']"));  //  Aranan ürün var
			System.out.println("Ürün buldundu : " + result.getText());
		} catch (Exception e) {
			result = driver.findElement(By.xpath(".//*[@id='noResultsTitle']")); // Aranan ürün yok
			System.out.println("Aranan ürün yok : " + result.getText());
			driver.quit();
			driver.close();
		}
	}
	
	public void secondPage(){
		WebElement secondpage;
		try {
			secondpage = driver.findElement(By.xpath(".//*[@id='pagn']/span[3]/a"));
			secondpage.click();
			System.out.println("2. sayfaya geçildi");
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
		// 3. ürüne 1 kere týklayýnca sayfa yenileniyor galiba bir hata var o yuzden ikinci atamayý yapýp tekrar týklýyorum giriyor
		
		System.out.println("3. Ürün seçildi");
	}
	
	public void getWishList(){
		/* Add Liste týklayýnca karþýmýza 2 sayfa çýkabiliyor.
		 * Muhtemelen internet hýzýna ve ya tarayýcýnýn o anki durumuna baglý olarak.
		 * Yeni sayfa açýlýrsa 1. yöntem, Ayný sayfada iþlem yapýlýrsa 2. yöntem kullanýlýyor */
		
		WebElement viewlist;
		
		try {                                       
			viewlist = driver.findElement(By.xpath(".//*[@id='WLHUC_viewlist']/span/span"));
			System.out.println(viewlist.getText());
			viewlist.click();		
			System.out.println("Yontem 1 ile acýldý");
		} catch (Exception e) {
			driver.navigate().back();
			System.out.println("Yontem 2 ile acýldý");
		}
		driver.findElement(By.xpath(".//*[@id='nav-link-accountList']/span[2]")).click(); //account&list'e týklar
		driver.findElement(By.xpath(".//*[@id='a-page']/div[3]/div/div[4]/div[1]/div/div/ul/li[6]/span/a")).click(); //list'i seçip týklar
	}
	
	public void isProductInList(String b){
		
		try {
			WebElement compare=driver.findElement(By.xpath(b)); // Bu atama yapýlýrsa Ürün listede var demektir
			System.out.println("Ürün wish listte var ve ismi : "+compare.getText());
		} catch (Exception e) {
			System.out.println("Ürün Listede Yok");
		}
	}

}
