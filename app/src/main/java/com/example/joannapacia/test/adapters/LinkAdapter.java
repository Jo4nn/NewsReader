package com.example.joannapacia.test.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joannapacia.test.R;
import com.example.joannapacia.test.R.layout;
import com.example.joannapacia.test.model.LinkData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joannapacia on 07/03/17.
 */

public class LinkAdapter extends ArrayAdapter<LinkData> {

    private Context mContext;
    List<LinkData> data = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public LinkAdapter(Context context, List<LinkData> data) {
        super(context, layout.list_item, data);
        this.mContext = context;
        this.data = data;
         mLayoutInflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public LinkData getItem(int position) {
        return data.get(position);
    }


    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    /**
     * Used to avoid calling "findViewById" every time the getView() method is called,
     * because this can impact to your application performance when your list is large
     */
    private class ViewHolder {
        protected TextView itemHeadline;
        protected TextView itemDescription;
        protected ImageView itemImage;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // create a ViewHolder reference
        ViewHolder holder = null;
        // trying to reuse a recycled view
        View vi = view;

        // check to see if the reused view is null or not, if is not null then reuse it
        if (vi == null) {
            holder = new ViewHolder();
            // the view is not a recycled one: we have to inflate
            view = mLayoutInflater.inflate(R.layout.list_item, viewGroup, false);

            // get all views you need to handle from the cell and save them in the view holder
            holder.itemHeadline = (TextView) view.findViewById(R.id.headline);
            holder.itemDescription = (TextView) view.findViewById(R.id.description);
            holder.itemImage = (ImageView) view.findViewById(R.id.picture);

            // save the view holder on the cell view to get it back latter
            view.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)view.getTag();
        }

        LinkData ld = data.get(position);
        // get the string item from the position "position" from array list to put it on the
        // TextView
        String stringHeadline = ld.getHeadline();
        String stringDecription = ld.getDesciption();
        String urlImage = ld.getImage();
        String stringLink = ld.getLink();

        if (stringHeadline != null && stringDecription != null && urlImage != null) {
            // set the item name on the TextView
            holder.itemHeadline.setText(stringHeadline);
            holder.itemDescription.setText(stringDecription);

            Picasso.with(mContext)
                    .load(urlImage)
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(400,400)
                    .centerCrop()
                    .into(holder.itemImage);
        } else {
            // Set the results into TextViews
            holder.itemHeadline.setText("Unknown");
            holder.itemDescription.setText("Unknown");
            holder.itemImage.setImageResource(R.mipmap.ic_launcher);
        }

        // used to pass details to ArticleActivity.class
        final java.util.HashMap<String, String> hashMap= new java.util.HashMap<>();
        hashMap.put("LINK", stringLink);
        hashMap.put("HEADLINE", stringHeadline);
        hashMap.put("URLIMAGE", urlImage);
        hashMap.put("DESCRIPTION", stringDecription);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, com.example.joannapacia.test.activities
                        .WebViewActivity.class);
                intent.putExtra("HASHMAP", hashMap);
                v.getContext().startActivity(intent);
            }
        });
        // this method must return the view corresponding to the data at the specified position.
        return view;
    }
}
