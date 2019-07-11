package com.tournity.App.Groups.Bloc.Controllers;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tournity.App.Groups.Repository.Models.GroupModel;
import com.tournity.App.Groups.View.Activities.ListGroupActivity;
import com.tournity.Repository.Enums.ModelError;
import com.tournity.Repository.Listeners.ModelListener;

import java.util.ArrayList;

public class GroupController {
    protected ListGroupActivity context;

    public GroupController(Context context) {
        this.context = (ListGroupActivity) context;
    }

    public void listGroups() {
        final ArrayList<String> listGroupName = new ArrayList<>();
        ModelListener grListener = new ModelListener<GroupModel>() {
            @Override
            public void onSuccess(GroupModel model) {

                for (int i = 0; i < model.getMyGroupArray().length; i++) {
                    listGroupName.add(model.getMyGroupArray()[i].getName());
                }
                ArrayAdapter adapter = new ArrayAdapter(context,
                        android.R.layout.simple_expandable_list_item_1, listGroupName);
                context.listView.setAdapter(adapter);
            }

            @Override
            public void onError(ModelError error) {

            }
        };
        GroupModel.listGroups(context, grListener);
    }

    public void create(String name) {
        if (validateNameLength(name)) {
            ModelListener crGrListener = new ModelListener() {
                @Override
                public void onSuccess(Object model) {
                    Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(ModelError error) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            };
            GroupModel.assemble(context, name, crGrListener);
        } else {
            Toast.makeText(context, "Invalid name", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validateNameLength(String name) {
        return name.length() >= 3;
    }
}
