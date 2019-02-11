package br.com.caelum.casadocodigo.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.caelum.casadocodigo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws InterruptedException {

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.lista_livros), isDisplayed()));

        Thread.sleep(5000);

        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction buttonComprarFisico = onView(
                allOf(withId(R.id.detalhes_livro_comprar_fisico)));
        buttonComprarFisico.perform(scrollTo(), click());

        goToCarrinhoAndBackToList();

        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction buttonComprarEbook = onView(
                allOf(withId(R.id.detalhes_livro_comprar_ebook)));
        buttonComprarEbook.perform(scrollTo(), click());

        goToCarrinhoAndBackToList();

        recyclerView.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction buttonComprarAmbos = onView(
                allOf(withId(R.id.detalhes_livro_comprar_ambos)));
        buttonComprarAmbos.perform(scrollTo(), click());

        goToCarrinhoAndBackToList();

    }

    private void goToCarrinhoAndBackToList() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.vai_para_carrinho), withContentDescription("Carrinho"), isDisplayed()));
        actionMenuItemView.perform(click());

        pressBack();

        pressBack();
    }
}
