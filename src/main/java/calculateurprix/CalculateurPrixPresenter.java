package calculateurprix;


import javax.swing.*;

public class CalculateurPrixPresenter {

    public enum Pays {
        Belgique("Belgique", 21), Danemark("Danemark",25), Allemagne("Allemagne",19), Grèce("Grèce",23), Espagne("Espagne",21), France("France",20), Italie("Italie",22), Chypre("Chypre",19), Luxembourg("Luxembourg",15), Pays_Bas("Pays Bas",21), Autriche("Autriche",20), Portugal("Portugal",23), Suède("Suède",25);
        private final String key;
        private final Integer value;
        Pays(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
        public Integer getValue() { return value; }
        public String toString() { return key; }
    }
    private final CalculateurPrixView calculateurPrixView;

    public CalculateurPrixPresenter(CalculateurPrixView calculateurPrixView) {
        this.calculateurPrixView = calculateurPrixView;
    }

    public void onComputeButtonClicked(String montantArticleAsText, String quantiteArticleAsText, Pays pays) {
        try{
            float montant = Float.valueOf(montantArticleAsText)*Integer.valueOf(quantiteArticleAsText);
            calculateurPrixView.afficherMontantHT(montant);
            calculateurPrixView.afficherMontantTTC(montant*(pays.getValue()/100f+1));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Les données fournies sont erronées", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }

}