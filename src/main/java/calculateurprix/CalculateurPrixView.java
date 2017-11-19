package calculateurprix;


import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

public class CalculateurPrixView extends JFrame {

    private final CalculateurPrixPresenter presenter;
    private JFormattedTextField montantHTTextField;
    private JFormattedTextField montantTTCTextField;

    public CalculateurPrixView() throws HeadlessException {
        super("Calculateur de prix");
        this.presenter = new CalculateurPrixPresenter(this);

        JLabel prixArticleLabel = new JLabel("Prix d'un article (€) : ");
        JTextField prixArticleTextField = new JTextField(10);
        prixArticleLabel.setLabelFor(prixArticleTextField);
        prixArticleTextField.setToolTipText("Entrez ici le montant d'un article");

        JLabel quantiteLabel = new JLabel("Quantité : ");
        //quantiteLabel.setMinimumSize(new Dimension(100, 100));
        //quantiteLabel.setPreferredSize(new Dimension(10,20));
        //quantiteLabel.setMaximumSize(new Dimension(100, 100));
        JTextField quantiteTextField = new JTextField(10);
        quantiteLabel.setLabelFor(quantiteTextField);
        quantiteTextField.setToolTipText("Entrez ici la quantité d'article");

        JLabel paysLabel = new JLabel("Pays : ");
        JComboBox<CalculateurPrixPresenter.Pays> paysJComboBox = new JComboBox<>(CalculateurPrixPresenter.Pays.values());
        paysLabel.setLabelFor(paysJComboBox);

        JLabel montantHTLabel = new JLabel("Montant HT : ");
        montantHTTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        montantHTTextField.setEditable(false);
        montantHTLabel.setLabelFor(montantHTTextField);

        JLabel montantTTCLabel = new JLabel("Montant TTC (France) : ");
        montantTTCTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        montantTTCTextField.setEditable(false);
        montantTTCLabel.setLabelFor(montantTTCTextField);

        JButton computeButton = new JButton("Calculer");
        computeButton.addActionListener(e -> this.presenter.onComputeButtonClicked(prixArticleTextField.getText(), quantiteTextField.getText(),(CalculateurPrixPresenter.Pays) paysJComboBox.getSelectedItem()));


        JPanel contentPane = new JPanel(new GridLayout(0,3));
        setContentPane(contentPane);

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(prixArticleLabel);
        labelPane.add(quantiteLabel);
        labelPane.add(paysLabel);
        labelPane.add(montantHTLabel);
        labelPane.add(montantTTCLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(prixArticleTextField);
        fieldPane.add(quantiteTextField);
        fieldPane.add(paysJComboBox);
        fieldPane.add(montantHTTextField);
        fieldPane.add(montantTTCTextField);

        JPanel buttonPane = new JPanel(new GridBagLayout());
        buttonPane.add(computeButton);

        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane);
        add(fieldPane);
        add(buttonPane);


        prixArticleTextField.requestFocus();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void afficherMontantHT(float montant){
        this.montantHTTextField.setValue(montant);
    }
    public void afficherMontantTTC(float montant){
        this.montantTTCTextField.setValue(montant);
    }

    public void afficherErreur(String message) {
        showMessageDialog(this, message, "Erreur", ERROR_MESSAGE);
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}