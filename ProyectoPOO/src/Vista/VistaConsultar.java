package Vista;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JOptionPane;
public class VistaConsultar extends javax.swing.JFrame {

    
    public VistaConsultar() {
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombreConsulta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtForma = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtVariedad = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNombreConsulta1 = new javax.swing.JTextField();
        txtCiudad1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreConsulta.setBackground(new java.awt.Color(255, 255, 204));
        txtNombreConsulta.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtNombreConsulta.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreConsultaActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombreConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 78, 249, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Ubicación de las Atracciones:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, 29));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Descripción");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 223, 113, 23));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Ubiacación");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 365, -1, 26));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Precio Entrada");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 410, 128, -1));

        txtForma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormaActionPerformed(evt);
            }
        });
        getContentPane().add(txtForma, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 223, 235, 124));

        txtCiudad.setToolTipText("");
        getContentPane().add(txtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 370, 233, -1));
        getContentPane().add(txtVariedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 410, 233, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Nombre de la Atraccion a consultar:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 310, 29));

        txtNombreConsulta1.setBackground(new java.awt.Color(255, 255, 204));
        txtNombreConsulta1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtNombreConsulta1.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreConsulta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreConsulta1ActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombreConsulta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 37, 249, -1));

        txtCiudad1.setToolTipText("");
        getContentPane().add(txtCiudad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 183, 233, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Nombre");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 183, -1, 26));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 204));
        btnConsultar.setText("Consultar");
        getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 116, 119, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/FondoConsulta.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFormaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormaActionPerformed

    private void txtNombreConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreConsultaActionPerformed

    private void txtNombreConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreConsulta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreConsulta1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConsultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JTextField txtCiudad;
    public javax.swing.JTextField txtCiudad1;
    public javax.swing.JTextField txtForma;
    public javax.swing.JTextField txtNombreConsulta;
    public javax.swing.JTextField txtNombreConsulta1;
    public javax.swing.JTextField txtVariedad;
    // End of variables declaration//GEN-END:variables


   public void variedadPasstas(int a , int b){
       JOptionPane.showMessageDialog(null, "Existen "+a+" registros de pastas variedad fresca y "+b +" registros de pastas variedad seca");
   }
   public void ListaFormas (List<String> listaFormas){
       JOptionPane.showMessageDialog(null, "Las formas de pastas almacenadas son : "+listaFormas);
   }
   public void CantidadEnCiudad(int a){
      JOptionPane.showMessageDialog(null, "De esta ciudad son originarias "+a+" pastas de las que se encuentran registradas");
}
   public void error(){
       JOptionPane.showMessageDialog(null, "Inserte un nombre para consultar");
   }
   public void noExiste(){
       JOptionPane.showMessageDialog(null, "No existe registro de esta pasta", "Error", JOptionPane.ERROR_MESSAGE);
   }
   

          
}
