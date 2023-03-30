package devandroid.elismar.applistacurso.controller;

import java.util.ArrayList;
import java.util.List;

import devandroid.elismar.applistacurso.model.Curso;

public class CursoController {

    private List listCursos;

    public List getListaDeCursos() {

        listCursos = new ArrayList<Curso>();

        listCursos.add(new Curso("Java"));
        listCursos.add(new Curso("Html"));
        listCursos.add(new Curso("Css"));
        listCursos.add(new Curso("JavaScript"));
        listCursos.add(new Curso("Angular"));
        listCursos.add(new Curso("React"));
        listCursos.add(new Curso("Vue"));

        return listCursos;
    }

    public ArrayList<String> dadosParaSpinner() {
        ArrayList<String> dados = new ArrayList<>();

        for (int i = 0; i < getListaDeCursos().size(); i++) {
            Curso objeto = (Curso) getListaDeCursos().get(i);
            dados.add(objeto.getNomeDoCursoDesejado());
        }

        return dados;
    }

}
