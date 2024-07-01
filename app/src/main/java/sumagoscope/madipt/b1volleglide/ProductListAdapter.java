package sumagoscope.madipt.b1volleglide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {


    ArrayList<Product> list;

    public ProductListAdapter(ArrayList<Product> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {

        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvPrice.setText(""+list.get(position).getPrice());
        holder.tvCategory.setText(list.get(position).getCategory());

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getThumbnail())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvCategory;
        TextView tvPrice;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvCategory=itemView.findViewById(R.id.tvCategory);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
