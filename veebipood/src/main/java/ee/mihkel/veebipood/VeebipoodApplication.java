package ee.mihkel.veebipood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeebipoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeebipoodApplication.class, args);
    }

}

//1.  04.08 --> kontroller, entity, repository
//2.  06.08 --> veateated, tabelite sidumine
//3.  12.08 --> hunnik entity'd, projekti mahu kasvatamine, service
//4.  14.08 --> rendipoe alustamine
//5.  18.08 --> unit testid
//6.  21.08 --> RestTemplate
//7. 25.08 --> front-end (React)
//8. 27.08 --> front-end, ADMIN, lisamine, kustutamine
//9. 02.09/04.09 T/N õhtu. 18.00
//10. 07.09 P hommik. 9.00 ----> hüpikaknad. MUI. tõlge
//11. 09.09 T õhtu ----> 18.30-20.20 localStorage. kaardirakendus. serverisse ülespanek
//12. 14.09 P hommik. 11.00 ----> Autentimine: API endpoints
//13. 18.09 N õhtu 17.30 ----> JWT, ModelMapper, Logid,
//14. 23.09 T õhtu 17.30 ----> Rollid, e-maili saatmine, Erinevad Keskkonnad
//15. 28.09 P hommik 9.00 ----> Front-end URL kaitse, Front end võtab tokeni, salvestab, saadab jne
//16. 02.10 N õhtu 17.30
//17. 05.10 P hommik 9.00
//Kokku 18 kohtumist, viimane päev lõpuprojekti esitlemiseks