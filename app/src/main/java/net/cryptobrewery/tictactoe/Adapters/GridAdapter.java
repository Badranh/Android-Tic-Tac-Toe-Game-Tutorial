package net.cryptobrewery.tictactoe.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.cryptobrewery.tictactoe.Models.Cell;
import net.cryptobrewery.tictactoe.Models.Game;
import net.cryptobrewery.tictactoe.Interfaces.onCellClicked;
import net.cryptobrewery.tictactoe.R;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private onCellClicked listener;
    private Cell btnArray[][] = new Cell[3][3];

    public GridAdapter(onCellClicked listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,  int pos) {
       final Cell cell = new Cell(Game.State.NONE,viewHolder.btn);
       viewHolder.btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(listener != null)
                    listener.clicked(viewHolder.getAdapterPosition(),cell,btnArray);
           }
       });
       setCell(pos,cell);
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Button btn;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);

         }
    }


    public void setListener(onCellClicked listener) {
        this.listener = listener;
    }

    public void resetCells() {
        for(int i = 0 ;i  < 3 ; i ++ ){
            for(int j = 0 ;j  < 3 ; j ++ ){
                btnArray[i][j].setState(Game.State.NONE);
                btnArray[i][j].getBtn().setText("");
            }
        }
    }

    private void setCell(int pos,Cell cell){
        Log.d("alarm",""+pos);
        int x=-1,y=-1;
        if(pos >= 0 && pos<= 2 ){
            x=0;
            y= pos;
        }
        if(pos >=3 && pos <=  5){
            x=1;
            y= pos - 3;
        }
        if(pos >= 6){
            x=2;
            y= pos -6;
        }
         btnArray[x][y] = cell;
    }

}
