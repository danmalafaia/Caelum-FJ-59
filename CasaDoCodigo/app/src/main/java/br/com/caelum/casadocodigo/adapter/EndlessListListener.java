package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessListListener extends RecyclerView.OnScrollListener {

    private int quantidadeTotalItens;
    private int primeiroItemVisivel;
    private int quantidadeItensVisiveis;
    private boolean carregando = true;
    private int totalAnterior = 0;

    public abstract void carregaMaisItens();

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int qtdScrollHorizontal, int qtdScrollVertical) {
        super.onScrolled(recyclerView, qtdScrollHorizontal, qtdScrollVertical);

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager()
                ;
        quantidadeTotalItens = layoutManager.getItemCount();
        primeiroItemVisivel = layoutManager.findFirstVisibleItemPosition();
        quantidadeItensVisiveis = recyclerView.getChildCount();
        int limiteLimiteParaCarregar = quantidadeTotalItens - quantidadeItensVisiveis - 5;
        if (carregando) {
            if (quantidadeTotalItens > totalAnterior) {
                totalAnterior = quantidadeTotalItens;
                carregando = false;
            }
        }
        if (!carregando && primeiroItemVisivel >= limiteLimiteParaCarregar) {
            carregaMaisItens();
            carregando = true;
        }
    }
}
