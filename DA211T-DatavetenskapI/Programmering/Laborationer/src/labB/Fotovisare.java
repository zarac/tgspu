package labB;
import labA.*; // Foto

public class Fotovisare extends javax.swing.JFrame {
    private String[] kategorier = {"Ovrigt", "Familj", "Barnbarn", "Resor" };
    
    /**
     * En fotovisare som visar bilder i normal storlek skapas.
     */
    public Fotovisare() {
        this( ScaleIcon.NORMAL );    
    }
    
    /**
     * En fotovisare som visar bilden p� angivet s�tt visas. M�jliga s�tt �r:
     * <ul>
     * <li>ScaleIcon.NORMAL - bilden visas i normal storlek</li>
     * <li>ScaleIcon.SCALE - bilden skalas om s� att hela bilden syns</li>
     * <li>ScaleIcon.FIT - bilden skalas om s� att hela visningsytan anv�nds</li>
     * </ul>
     * @param state visnings�tt av bilden
     */
    public Fotovisare( int state ) {
        initComponents();
        if( state == ScaleIcon.FIT ) {
            rbFit.setSelected( true );
        } else if( state == ScaleIcon.NORMAL ) {
            rbNormal.setSelected( true );
        } else {
            rbScale.setSelected( true );
        }
    }
    
    /**
     * Anger Foto-objekt som ska visas av fotovisaren.
     * @param foto det foto som ska visas
     */
    public void setFoto( Foto foto ) {
        lblBild.setIcon( new ScaleIcon( foto.getLagringsplats(), getState() ) );
        lblMotiv.setText( foto.getMotiv() );
        lblKategori.setText( kategorier[ foto.getKategori() ] );
        lblArtal.setText( String.valueOf( foto.getArtal() ) );
        lblLagringsplats.setText( foto.getLagringsplats() );
    }
    
    /**
     * Anger visningss�tt av bilden. M�jliga s�tt �r:
     * <ul>
     * <li>ScaleIcon.NORMAL - bilden visas i normal storlek</li>
     * <li>ScaleIcon.SCALE - bilden skalas om s� att hela bilden syns</li>
     * <li>ScaleIcon.FIT - bilden skalas om s� att hela visningsytan anv�nds</li>
     * </ul>
     * @param state visningss�tt
     */
    public void setState( int state ) {
        ScaleIcon icon = ( ScaleIcon )lblBild.getIcon();
        icon.setState( state );
    }
    
    /**
     * Returnerar aktuellt visningss�tt av bilden
     * @return aktuellt visningss�tt
     */
    public int getState() {
        int state = ScaleIcon.FIT;
        if( rbNormal.isSelected() )
            state = ScaleIcon.NORMAL;
        else if( rbScale.isSelected() )
            state = ScaleIcon.SCALE;
        return state;
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        lblMotiv = new javax.swing.JLabel();
        lblBild = new javax.swing.JLabel();
        rbFit = new javax.swing.JRadioButton();
        rbNormal = new javax.swing.JRadioButton();
        rbScale = new javax.swing.JRadioButton();
        lblKategori = new javax.swing.JLabel();
        lblArtal = new javax.swing.JLabel();
        lblLagringsplats = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fotovisare");
        lblMotiv.setFont(new java.awt.Font("Arial", 1, 12));
        lblMotiv.setOpaque(true);
        getContentPane().add(lblMotiv);
        lblMotiv.setBounds(10, 0, 440, 20);

        lblBild.setBackground(new java.awt.Color(204, 255, 255));
        lblBild.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBild.setOpaque(true);
        getContentPane().add(lblBild);
        lblBild.setBounds(10, 20, 440, 330);

        buttonGroup1.add(rbFit);
        rbFit.setFont(new java.awt.Font("Arial", 1, 11));
        rbFit.setSelected(true);
        rbFit.setText("Fit");
        rbFit.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbFit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbFit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFitActionPerformed(evt);
            }
        });

        getContentPane().add(rbFit);
        rbFit.setBounds(10, 350, 140, 15);

        buttonGroup1.add(rbNormal);
        rbNormal.setFont(new java.awt.Font("Arial", 1, 11));
        rbNormal.setText("Normal");
        rbNormal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbNormal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNormalActionPerformed(evt);
            }
        });

        getContentPane().add(rbNormal);
        rbNormal.setBounds(160, 350, 140, 15);

        buttonGroup1.add(rbScale);
        rbScale.setFont(new java.awt.Font("Arial", 1, 11));
        rbScale.setText("Scale");
        rbScale.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbScale.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbScaleActionPerformed(evt);
            }
        });

        getContentPane().add(rbScale);
        rbScale.setBounds(310, 350, 140, 15);

        lblKategori.setFont(new java.awt.Font("Arial", 0, 9));
        lblKategori.setText("kategori");
        getContentPane().add(lblKategori);
        lblKategori.setBounds(10, 370, 140, 12);

        lblArtal.setFont(new java.awt.Font("Arial", 0, 9));
        lblArtal.setText("\u00e5rtal");
        getContentPane().add(lblArtal);
        lblArtal.setBounds(160, 370, 140, 12);

        lblLagringsplats.setFont(new java.awt.Font("Arial", 0, 9));
        lblLagringsplats.setText("Lagringsplats");
        getContentPane().add(lblLagringsplats);
        lblLagringsplats.setBounds(310, 370, 140, 12);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-469)/2, (screenSize.height-417)/2, 469, 417);
    }// </editor-fold>//GEN-END:initComponents

    private void rbScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbScaleActionPerformed
        setState( ScaleIcon.SCALE );
    }//GEN-LAST:event_rbScaleActionPerformed

    private void rbNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNormalActionPerformed
        setState( ScaleIcon.NORMAL );
    }//GEN-LAST:event_rbNormalActionPerformed

    private void rbFitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFitActionPerformed
        setState( ScaleIcon.FIT );
    }//GEN-LAST:event_rbFitActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fotovisare().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblBild;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblLagringsplats;
    private javax.swing.JLabel lblMotiv;
    private javax.swing.JLabel lblArtal;
    private javax.swing.JRadioButton rbFit;
    private javax.swing.JRadioButton rbNormal;
    private javax.swing.JRadioButton rbScale;
    // End of variables declaration//GEN-END:variables
    
}