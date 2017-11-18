package calculateurprix;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

public class CalculateurPrixView extends JFrame {

    private final CalculateurPrixPresenter presenter;
    private JFormattedTextField montantHTTextField;

    public CalculateurPrixView() throws HeadlessException {
        super("Calculateur de prix");
        this.presenter = new CalculateurPrixPresenter(this);

        JLabel prixArticleLabel = new JLabel("Prix d'un article (€) : ");
        JTextField prixArticleTextField = new JTextField(10);
        prixArticleLabel.setLabelFor(prixArticleTextField);
        prixArticleTextField.setToolTipText("Entrez ici le montant d'un article");

        JLabel quantiteLabel = new JLabel("Quantité : ");
        JTextField quantiteTextField = new JTextField(10);
        quantiteLabel.setLabelFor(quantiteTextField);
        quantiteTextField.setToolTipText("Entrez ici la quantité d'article");

        JLabel montantHTLabel = new JLabel("Montant HT : ");
        montantHTTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        montantHTTextField.setEditable(false);
        montantHTLabel.setLabelFor(montantHTTextField);

        JButton computeButton = new JButton("Calculer");
        computeButton.addActionListener(e -> this.presenter.onComputeButtonClicked(prixArticleTextField.getText(), quantiteTextField.getText()));

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(prixArticleLabel);
        labelPane.add(quantiteLabel);
        labelPane.add(montantHTLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(prixArticleTextField);
        fieldPane.add(quantiteTextField);
        fieldPane.add(montantHTTextField);

        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane);
        add(fieldPane);
        add(computeButton);

        prixArticleTextField.requestFocus();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void afficherMontant(float montant){
        this.montantHTTextField.setValue(montant);
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