package arj.fittrack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 2017-10-22.
 */

//this code is used to create an adaptor which is needed to take the data set so that it can be attached to the recycler view.
public class NotepadnoteAdapter extends RecyclerView.Adapter<NotepadnoteAdapter.MyViewHolder> {

    private List<NotesCreator> NotesCreatorList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, content;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);

        }
    }

    //this code will be going through a list called NotesCreatorList that has been built with my NotesCreator class.
    public NotepadnoteAdapter(List<NotesCreator> NotesCreatorList) {
        this.NotesCreatorList = NotesCreatorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notepadrecyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NotesCreator note = NotesCreatorList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return NotesCreatorList.size();
    }
 }






