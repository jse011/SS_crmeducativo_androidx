package com.consultoraestrategia.ss_crmeducativo.personalChat.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;




import com.consultoraestrategia.ss_crmeducativo.appmessage.R;

public class DateHolder   extends RecyclerView.ViewHolder  {


    TextView date;
    public DateHolder(@NonNull View itemView) {
        super(itemView);
        date=(TextView) itemView.findViewById(R.id.date);
    }
    public void bind(String dateConvert) {
        date.setText(dateConvert);
    }
}
