package gui;

import java.util.List;
import java.util.ArrayList;

import java.util.function.Supplier;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import util.DatabaseThread;
import util.Transaction;
import util.User;

/**
 *
 * @author JC
 */
public class ViewATransactionScreen extends javax.swing.JFrame {

    String type;
    DatabaseThread databaseThread;
    User currentUser;

    /**
     * Creates new form view transaction
     */
    public ViewATransactionScreen() {
        initComponents();
        setVisible(true);
    }

    public ViewATransactionScreen(DatabaseThread databaseThread) {
        this.databaseThread = databaseThread;
        this.currentUser = databaseThread.getCurrentUser();
        initComponents();
        setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        javax.swing.JScrollPane jScrollPane1;
        javax.swing.JLabel viewTransactions;
        javax.swing.JButton searchBttn;

        searchField = new javax.swing.JTextField();
        searchBttn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        transactionTable = new javax.swing.JTable();
        viewTransactions = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchField.addActionListener(this::searchFieldActionPerformed);

        searchBttn.setText("SEARCH");
        searchBttn.addActionListener(this::searchBttnActionPerformed);

        transactionTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Date", "Amount", "Category"
                }));
        jScrollPane1.setViewportView(transactionTable);

        viewTransactions.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        viewTransactions.setText("VIEW TRANSACTIONS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchField)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchBttn)))
                                .addGap(32, 32, 32))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(263, Short.MAX_VALUE)
                                .addComponent(viewTransactions)
                                .addGap(303, 303, 303)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(viewTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>

    private void searchBttnActionPerformed(java.awt.event.ActionEvent evt) {
        var input = searchField.getText();
        var transactions = databaseThread.getUserTransactions();
        Supplier<List<Transaction>> searchedTransaction = () -> {
            List<Transaction> searchVal = new ArrayList<>();
            for (Transaction tx : transactions) {
                if (tx.getCategory().getName().equals(input)) {
                    searchVal.add(tx);
                    break;
                }
            }
            return searchVal;
        };
        if (searchedTransaction.get() == null) {
            JOptionPane.showMessageDialog(this, "No results Found", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel tblModel = (DefaultTableModel) transactionTable.getModel();
            for (Transaction tx : searchedTransaction.get()) {
                String[] data = { tx.getDate().toString(), String.valueOf(tx.getAmount()), tx.getCategory().getName() };
                tblModel.addRow(data);
            }
        }
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewATransactionScreen.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ViewATransactionScreen().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JTable transactionTable;
    private javax.swing.JTextField searchField;

    // End of variables declaration
}
