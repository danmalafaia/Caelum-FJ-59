package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.utils.LivrosDelegate;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LivroAdapter extends RecyclerView.Adapter {

    private final List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int tipoDeLayout = R.layout.item_livro_par;
        if (viewType % 2 != 0) {
            tipoDeLayout = R.layout.item_livro_impar;
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(tipoDeLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder myViewHolder = (ViewHolder) viewHolder;
        Livro livro = livros.get(i);
        myViewHolder.nome.setText(livro.getNome());
        Picasso.with(myViewHolder.foto.getContext())
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(myViewHolder.foto);
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_livro_nome)
        TextView nome;
        @BindView(R.id.item_livro_foto)
        ImageView foto;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.item_livro)
        public void clickItem() {
            Livro livro = livros.get(getAdapterPosition());
            LivrosDelegate delegate = (LivrosDelegate) itemView.getContext();
            delegate.lidaComLivroSelecionado(livro);
        }
    }
}
