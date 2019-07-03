
package com.tournity.BussinessLogic.Controllers;

import android.content.Context;

import com.tournity.DataModels.TournamentModel;

import java.util.ArrayList;

public class TournamentController {
    private Context context;
    private TournamentModel tournamentModel;

    public TournamentController(Context context) {
        this.context = context;
        this.tournamentModel=new TournamentModel(context);
    }
    public TournamentController() {

        this.tournamentModel=new TournamentModel();
    }
public ArrayList<TournamentModel> getAllTournaments()throws Exception{

        if(this.tournamentModel.getAll()!=null) {
            return this.tournamentModel.getAll();
        }else{
            throw new Exception("No se encontraron resultados");
    }



}


}
