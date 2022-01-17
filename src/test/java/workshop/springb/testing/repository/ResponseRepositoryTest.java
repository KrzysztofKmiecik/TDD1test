package workshop.springb.testing.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import workshop.springb.testing.model.Response;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.*;

/*
    TODO 2 README
    Podejrzyj javadoc dla poniższej adnotacji - opakowuje kilka innych.

    W skrócie -  @DataJpaTest wyłącza pełną autokonfigurację, pozostawiając tylko część istotną
    dla testów warstwy bazodanowej.
    Domyślnie testy z adnotacją @DataJpaTest będą używały bzy danych typu embedded / in-memory.
    Każdy test będzie uruchomiony w springowej transakcji.
 */
@DataJpaTest
@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
class ResponseRepositoryTest {

    /*
        TODO 3 README
        Prosimy Spring o wstrzyknięcie repozytorium
     */
    @Autowired
    private ResponseRepository responseRepository;

    /*
        TODO 4 README
        Metoda testowa -  zapisujemy Response w bazie przy użyciu save, sprawdzamy zapisany obiekt
     */
    @Test
    @DisplayName("Should save one Response with id 1L")
    public void shouldSaveAresponseWithId1() {
        assertTrue(responseRepository.findAll().isEmpty());
        Response savedResponse = responseRepository.save(new Response("Hello, World!", LocalDateTime.now()));
        assertAll(
                () -> assertEquals(1, responseRepository.findAll().size()),
                () -> assertEquals(savedResponse.getId(), responseRepository.findById(1L).get().getId()),
                () -> assertEquals(savedResponse.getGreeting(), responseRepository.findById(1L).get().getGreeting())
        );
    }

    /*
 _______________________________________________________________________________________________________________________
 TODO 1 README

 W tym kroku, skupimy się na testowaniu warstwy repozytorium.

 Mamy już jeden test, przejdźmy po todos 2-4, dla zapoznanaia się z klasą / metodą testową, następnie wybierz
 minimum 3 metody z repozytorium i napisz do nich testy

 Wskazówka - poczytaj w dokumentacji o @DirtiesContext

 Dla tego zadania nie ma gotowego rozwiązania - na tym etapie, nie powinno stanowić dla Ciebie problemu nie do roziwązania :)

 _______________________________________________________________________________________________________________________

 */

    @Test
    @DisplayName("Should delete one")
    void shouldDeleteResponse() {

        assertTrue(responseRepository.findAll().isEmpty());
        Response response = responseRepository.save(new Response("Hello, world!", LocalDateTime.now()));

        responseRepository.delete(response);
        assertTrue(responseRepository.findAll().isEmpty());
        //fail();

    }

    @Test
    @DisplayName("Should update response greeting")
    void shouldUpdateResponseGreeting() {
        assertTrue(responseRepository.findAll().isEmpty());
        Response response = responseRepository.save(new Response("Hello, world!", LocalDateTime.now()));
        response.setGreeting("Hello, Poland!");

        Response upadatedResponse = responseRepository.save(response);

        assertAll(
                () -> assertEquals(1, responseRepository.findAll().size()),
                () -> assertEquals("Hello, Poland!", responseRepository.findById(response.getId()).orElse(new Response("", LocalDateTime.now())).getGreeting())
        );
        //fail();
    }

    @Test
    @DisplayName("Should findAll")
    void shouldFindAll(){
        assertTrue(responseRepository.findAll().isEmpty());
        Response response1 = responseRepository.save(new Response("1", LocalDateTime.now()));
        Response response2 = responseRepository.save(new Response("2",LocalDateTime.now()));
        Response response3 = responseRepository.save(new Response("3",LocalDateTime.now()));

        assertAll(
                ()->assertEquals(3,responseRepository.findAll().size()),
                ()->assertEquals("1",responseRepository.findById(response1.getId()).orElse(new Response()).getGreeting()),
                ()->assertEquals("2",responseRepository.findById(response2.getId()).orElse(new Response()).getGreeting()),
                ()->assertEquals("3",responseRepository.findById(response3.getId()).orElse(new Response()).getGreeting())

        );
//fail();
    }

}

