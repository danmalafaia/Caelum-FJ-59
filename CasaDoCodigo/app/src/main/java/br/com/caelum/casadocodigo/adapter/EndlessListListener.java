package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessListListener extends RecyclerView.OnScrollListener {

    private int totalAnterior = 0;
    private boolean carregando = true;
    private int primeiroItemVisivel = 0;
    private int quantidadeTotalItens = 0;
    private int quantidadeItensVisiveis = 0;
    LinearLayoutManager layoutManager;

    public abstract void carregaMaisItens();

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int qtdScrollHorizontal, int qtdScrollVertical) {
        super.onScrolled(recyclerView, qtdScrollHorizontal, qtdScrollVertical);
        layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        quantidadeItensVisiveis = recyclerView.getChildCount();
        quantidadeTotalItens = layoutManager.getItemCount();
        primeiroItemVisivel = layoutManager.findFirstVisibleItemPosition();

        if (carregando) {
            if (quantidadeTotalItens > totalAnterior) {
                carregando = false;
                totalAnterior = quantidadeTotalItens;
            }
        }

        int limite = quantidadeTotalItens - quantidadeItensVisiveis;

        if (!carregando && primeiroItemVisivel >= limite) {
            carregaMaisItens();
            carregando = true;
        }
    }
}
