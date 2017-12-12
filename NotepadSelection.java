package arj.fittrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

 // this code is using a LinearLayoutManager and using the adapter to create the RecyclerView to be able to shows the notes.
public class NotepadSelection extends AppCompatActivity {

        private List<NotesCreator> NotesCreatorList = new ArrayList<>();
        private NotepadnoteAdapter nAdapter;
        private RecyclerView notesRecycler;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_notepad_selection);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  Open("Note.txt");
                }
            });

            notesRecycler = (RecyclerView) findViewById(R.id.notes);

            nAdapter = new NotepadnoteAdapter(NotesCreatorList);
            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getApplicationContext());
            notesRecycler.setLayoutManager(mLayoutManager);
            notesRecycler.setItemAnimator(new DefaultItemAnimator());
            notesRecycler.setAdapter(nAdapter);

            prepareNotes();

        }

        //this method is opening up and looking through the files that are in the internal storage directory
        private void prepareNotes() {
            File directory;
            directory = getFilesDir();
            File[] files = directory.listFiles();
            String theFile;
            for (int f = 1; f <=files.length; f++){
                theFile = "Note" + f + ".txt";
                NotesCreator note = new NotesCreator(theFile, Open(theFile));
                NotesCreatorList.add(note);
            }

        }

    //this will allow the user to open the note that was stored in the file that they saved it to.
        public String Open(String fileName) {
            String content = "";
            try {
                InputStream in = openFileInput(fileName);
                if (in != null) {
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    }
                    in.close();

                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {
            } catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }

            return content;
        }
    }



