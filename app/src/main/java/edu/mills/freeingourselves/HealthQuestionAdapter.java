package edu.mills.freeingourselves;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * An adapter for the HealthCareProviderFragment.
 * <p>
 * Modified from http://lalit3686.blogspot.in/2012/06/today-i-am-going-to-show-how-to-deal.html
 */
class HealthQuestionAdapter extends ArrayAdapter<HealthCareProviderFragment.Model> {

    private final Activity context;
    private final List<HealthCareProviderFragment.Model> list;

    HealthQuestionAdapter(Activity context, List<HealthCareProviderFragment.Model> list) {
        super(context, R.layout.row, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.row, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.label);
            viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.check);
            viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    list.get(position).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.

                    //save in database
                    Object[] params = new Object[]{context, position + 1, isChecked};
                    new UpdateSaved().execute(params);
                }
            });
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.label, viewHolder.text);
            convertView.setTag(R.id.check, viewHolder.checkbox);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.checkbox.setTag(position); // This line is important.
        viewHolder.text.setText(list.get(position).getName());
        viewHolder.checkbox.setChecked(FreeingOurselvesDatabaseUtilities.healthcareIsSaved(context, position + 1));
        return convertView;
    }

    private class UpdateSaved extends AsyncTask<Object, Void, Boolean> {
        protected Boolean doInBackground(Object... objects) {
            Activity activity = (Activity) objects[0];
            int position = (int) objects[1];
            boolean isChecked = (boolean) objects[2];
            return FreeingOurselvesDatabaseUtilities.updateSaved(activity, position, isChecked);
        }

        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(context, "Could not save questions.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private static class ViewHolder {
        TextView text;
        CheckBox checkbox;
    }
}
