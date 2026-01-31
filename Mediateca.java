package po.gamed;


import Poo.Gamed.exception.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Mediateca  implements Serializable  {


    private ArrayList<Obra> obras;
    private ArrayList<Utente> utentes;
    private ArrayList<Requisicao> requisicoes;

    public Mediateca() {
        obras = new ArrayList<>();
        utentes = new ArrayList<>();
        requisicoes = new ArrayList<>();
    }

    public void addObra(Obra obra) {
        obras.add(obra);
    }

    public void addUtente(Utente utente) {
        utentes.add(utente);
    }

    public Utente getUtente(int id) throws NoSuchUserException {
        return utentes.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchUserException("Utente não existe"));
    }

    public Obra getObra(String id) throws NoSuchWorkException {
        return obras.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchWorkException("Obra não existe"));
    }
    public ArrayList<Obra> getObraAll(){

        return obras;
    }
    public String getAllUtente(){
       String dados="<html>";
        int seq = 1;
        for (Utente utente: utentes ){
           dados+= utente.getId()+" - "+utente.getNome() +" - "+utente.getEmail()+" - Ativo<br>";
        }
        return dados;
    }


    public void requisitarObra(int userId, String obraId) throws Exception {
        Utente utente = getUtente(userId);
        Obra obra = getObra(obraId);

        // Regras:
        if (obra.getCategoria().equalsIgnoreCase("Obras de referência")) {
            JOptionPane.showMessageDialog(null,"Não pode requisitar obras de referência.");
            throw new RuleFailedException("Não pode requisitar obras de referência.");
        }
        if (obra.getPreco() > 10000) {
            JOptionPane.showMessageDialog(null,"Preço superior a 10.000 Kz.");
            throw new RuleFailedException("Preço superior a 10.000 Kz.");
        }
        if (obra.getExemplaresDisponiveis() <= 0) {
            JOptionPane.showMessageDialog(null,"Sem exemplares disponíveis.");
            throw new RuleFailedException("Sem exemplares disponíveis.");
        }
        long count = requisicoes.stream()
                .filter(r -> r.getUtente().getId() == userId)
                .count();
        if (count >= 2) {
            JOptionPane.showMessageDialog(null,"Não pode requisitar mais que 2 obras.");
            throw new RuleFailedException("Não pode requisitar mais que 2 obras.");
        }
        boolean already = requisicoes.stream()
                .anyMatch(r -> r.getUtente().getId() == userId && r.getObra().getId().equals(obraId));
        if (already) {
            JOptionPane.showMessageDialog(null,"Já requisitou esta obra.");
            throw new RuleFailedException("Já requisitou esta obra.");
        }

        obra.reduzirDisponiveis();
        requisicoes.add(new Requisicao(utente, obra));
    }

    public void devolverObra(int userId, String obraId) throws Exception {
        Requisicao req = requisicoes.stream()
                .filter(r -> r.getUtente().getId() == userId && r.getObra().getId().equals(obraId))
                .findFirst()
                .orElseThrow(() -> new WorkNotBorrowedByUserException("Obra não requisitada por este utente.") );

        req.getObra().aumentarDisponiveis();
        requisicoes.remove(req);
    }



    // Serialização
    public void save(String fileName) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(this);
        JOptionPane.showMessageDialog(null,"Salvo com sucesso");
        oos.close();
    }

    public static Mediateca load(String fileName) throws FileOpenFailedException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            Mediateca med = (Mediateca) ois.readObject();
            ois.close();
            return med;
        } catch (Exception e) {
            throw new FileOpenFailedException("Falha ao abrir o ficheiro.");
        }
    }
}

