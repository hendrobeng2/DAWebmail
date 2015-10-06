package rish.crearo.dawebmaillite.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import rish.crearo.dawebmaillite.database.EmailMessage;
import rish.crearo.dawebmaillite.scraper.ScrapingMachine;
import rish.crearo.dawebmaillite.database.User;

/**
 * Created by rish on 6/10/15.
 */
public class DeleteMail extends AsyncTask<Void, Void, Void> {

    Context context;
    DeleteMailListener deleteMailListener;
    boolean result = false;
    String username, pwd;
    ArrayList<EmailMessage> emailToBeDeleted;

    public DeleteMail(Context context, DeleteMailListener deleteMailListener, ArrayList<EmailMessage> emailsToBeDeleted) {
        this.deleteMailListener = deleteMailListener;
        this.emailToBeDeleted = emailsToBeDeleted;
        this.context = context;
        username = User.getUsername(context);
        pwd = User.getPassword(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        deleteMailListener.onPreDelete();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        result = new ScrapingMachine(username, pwd, context).getValues_forDelete(emailToBeDeleted);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        deleteMailListener.onPostDelete(result);
    }
}
