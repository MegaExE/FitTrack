package arj.fittrack;

/**
 * Created by adrian on 2017-10-22.
 */

public class NotesCreator {

        //this code will be used to build the note object
        private String title,
                content;

        public NotesCreator() {

        }

        public NotesCreator(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }

