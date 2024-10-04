package edu.avanzada.parcial1.vista;

import edu.avanzada.parcial1.control.ControlPrincipal;

/**
 *
 * @author Ana, Samuel, Juan
 */
public class VentanaRegistrarRaza extends javax.swing.JFrame {
    private ControlPrincipal control;
    
    /**
     * Creates new form VentanaRegistrarRaza
     */
    public VentanaRegistrarRaza(ControlPrincipal aThis) {
        control = aThis;
        initComponents();
        setLocationRelativeTo(null);
        SeccionFCI.setSelectedItem(null);
    }
    
    public void limpiar(){
        TextApariencia.setText("");
        TextCola.setText("");
        TextColor.setText("");
        TextEspalda.setText("");
        TextLomo.setText("");
        TextPais.setText("");
        TextPecho.setText("");
        TextPelo.setText("");
        TextRaza.setText("");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TextApariencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextRaza = new javax.swing.JTextField();
        TextPais = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TextEspalda = new javax.swing.JTextField();
        TextPelo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TextCola = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TextColor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TextLomo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TextPecho = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        SeccionFCI = new javax.swing.JComboBox<>();
        GrupoFCI = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        PanelBotones = new javax.swing.JPanel();
        BotonConsultar = new javax.swing.JButton();
        BotonInsertar = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        BotonModificar = new javax.swing.JButton();
        BotonLimpiar = new javax.swing.JButton();
        BotonSerializar = new javax.swing.JButton();
        PanelBaseDeDatos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        BotonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(228, 228, 255));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Bienvenido");

        PanelDatos.setBackground(new java.awt.Color(204, 204, 255));
        PanelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monospaced", 0, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Raza:");

        TextApariencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextAparienciaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Pais Origen:");

        TextRaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextRazaActionPerformed(evt);
            }
        });

        TextPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPaisActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Apariencia: ");

        jLabel5.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Pelo:");

        jLabel6.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Color (Manto):");

        TextEspalda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextEspaldaActionPerformed(evt);
            }
        });

        TextPelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPeloActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Espalda:");

        TextCola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextColaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Lomo:");

        TextColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextColorActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Cola:");

        TextLomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextLomoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Pecho:");

        TextPecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPechoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel11.setText("Clasificacion");

        jLabel12.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Grupo:");

        GrupoFCI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Grupo I: perros de pastor y perros boyeros.", "Grupo II: perros Pinscher, Schnauzer, Molosoide y perros Boyeros Suizos.", "Grupo III: perros Terriers.", "Grupo IV: perros Dachshund o Teckel.", "Grupo V: perros tipo Spitz y tipo primitivo.", "Grupo VI: perros tipo sabueso y perros de rastreo.", "Grupo VII: perros de muestra.", "Grupo VIII: perros cobradores, cazadores y perros de aguas.", "Grupo IX: perros de compañía.", "Grupo X: perros lebreles." }));
        GrupoFCI.setBorder(null);
        GrupoFCI.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                GrupoFCIItemStateChanged(evt);
            }
        });
        GrupoFCI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GrupoFCIMouseClicked(evt);
            }
        });
        GrupoFCI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrupoFCIActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Seccion:");

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextRaza, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(TextPais)
                            .addComponent(TextApariencia))
                        .addGap(70, 70, 70)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GrupoFCI, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextPelo, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(TextColor)
                            .addComponent(TextEspalda))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosLayout.createSequentialGroup()
                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(29, 29, 29))
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(27, 27, 27)))
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TextPecho, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(TextLomo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(TextCola, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SeccionFCI, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TextRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(TextLomo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(TextPelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TextPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(TextApariencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextEspalda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextPecho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(SeccionFCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(GrupoFCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        PanelBotones.setBackground(new java.awt.Color(188, 188, 234));
        PanelBotones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Operaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monospaced", 0, 14), new java.awt.Color(102, 0, 102))); // NOI18N

        BotonConsultar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonConsultar.setText("Consultar");
        BotonConsultar.setBorder(null);

        BotonInsertar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonInsertar.setText("Insertar");
        BotonInsertar.setBorder(null);

        BotonEliminar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonEliminar.setText("Eliminar");
        BotonEliminar.setBorder(null);

        BotonModificar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonModificar.setText("Modificar");
        BotonModificar.setBorder(null);

        BotonLimpiar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonLimpiar.setText("Limpiar");
        BotonLimpiar.setBorder(null);

        BotonSerializar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonSerializar.setText("Serializar");
        BotonSerializar.setBorder(null);

        javax.swing.GroupLayout PanelBotonesLayout = new javax.swing.GroupLayout(PanelBotones);
        PanelBotones.setLayout(PanelBotonesLayout);
        PanelBotonesLayout.setHorizontalGroup(
            PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBotonesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(BotonInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(BotonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(BotonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(BotonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(BotonSerializar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(BotonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        PanelBotonesLayout.setVerticalGroup(
            PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonSerializar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        PanelBaseDeDatos.setBackground(new java.awt.Color(165, 165, 229));
        PanelBaseDeDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Base De Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monospaced", 0, 14), new java.awt.Color(102, 0, 102))); // NOI18N

        Tabla.setBackground(new java.awt.Color(204, 204, 255));
        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "raza", "paisOrigen", "grupoFCI", "seccionFCI", "apariencia", "peloManto", "colorManto", "espalda", "lomo", "cola", "pecho"
            }
        ));
        jScrollPane2.setViewportView(Tabla);

        javax.swing.GroupLayout PanelBaseDeDatosLayout = new javax.swing.GroupLayout(PanelBaseDeDatos);
        PanelBaseDeDatos.setLayout(PanelBaseDeDatosLayout);
        PanelBaseDeDatosLayout.setHorizontalGroup(
            PanelBaseDeDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBaseDeDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        PanelBaseDeDatosLayout.setVerticalGroup(
            PanelBaseDeDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBaseDeDatosLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        BotonSalir.setText("Salir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(359, 359, 359)
                .addComponent(BotonSalir)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelBaseDeDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BotonSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelBaseDeDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextAparienciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextAparienciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextAparienciaActionPerformed

    private void TextRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextRazaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextRazaActionPerformed

    private void TextPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPaisActionPerformed

    private void TextEspaldaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextEspaldaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextEspaldaActionPerformed

    private void TextPeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPeloActionPerformed

    private void TextColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextColaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextColaActionPerformed

    private void TextColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextColorActionPerformed

    private void TextLomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextLomoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextLomoActionPerformed

    private void TextPechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPechoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPechoActionPerformed

    private void GrupoFCIItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GrupoFCIItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_GrupoFCIItemStateChanged

    private void GrupoFCIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrupoFCIMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_GrupoFCIMouseClicked

    private void GrupoFCIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrupoFCIActionPerformed

        if (GrupoFCI.getSelectedItem().equals("Grupo I: perros de pastor y perros boyeros.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: Perros de pastor");
            SeccionFCI.addItem("Sección 2: Perros boyeros");

        } else if (GrupoFCI.getSelectedItem().equals("Grupo II: perros Pinscher, Schnauzer, Molosoide y perros Boyeros Suizos.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: Perros Pinscher y Schanuzer");
            SeccionFCI.addItem("Sección 2: Perros Molosoides");
            SeccionFCI.addItem("Sección 3: Perros tipo montaña y boyeros suizos");

        } else if (GrupoFCI.getSelectedItem().equals("Grupo III: perros Terriers.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: Terriers de talla grande y media");
            SeccionFCI.addItem("Sección 2: Terriers de talla pequeña");
            SeccionFCI.addItem("Sección 3: Terriers tipo bull");
            SeccionFCI.addItem("Sección 4:Terriers de compañia");

        } else if (GrupoFCI.getSelectedItem().equals("Grupo IV: perros Dachshund o Teckel.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("");

        } else if (GrupoFCI.getSelectedItem().equals("Grupo V: perros tipo Spitz y tipo primitivo.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: Perros nórdico de trineo");
            SeccionFCI.addItem("Sección 2: Perros nórdicos de cacería");
            SeccionFCI.addItem("Sección 3: Perros nórdicos de guarda y pastoreo");
            SeccionFCI.addItem("Sección 4: Spitz europeos");
            SeccionFCI.addItem("Sección 5: Spitz asiáticos y razas semejantes");
            SeccionFCI.addItem("Sección 6: Tipo primitivo");
            SeccionFCI.addItem("Sección 7: Tipo primitivo - perros de caza");

        } else if (GrupoFCI.getSelectedItem().equals("Grupo VI: perros tipo sabueso y perros de rastreo.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: Perros tipo sabueso");
            SeccionFCI.addItem("Sección 2: Perros de rastro");
            SeccionFCI.addItem("Sección 3: Razas semejantes");

        }
        if (GrupoFCI.getSelectedItem().equals("Grupo VII: perros de muestra.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: Perros de muestra continentales");
            SeccionFCI.addItem("Sección 2: Perros de muestra ingleses e irlandeses");

        } else if (GrupoFCI.getSelectedItem().equals("Grupo VIII: perros cobradores, cazadores y perros de aguas.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: Perros cobradores de caza");
            SeccionFCI.addItem("Sección 2: Perros levantadores de caza");
            SeccionFCI.addItem("Sección 3: Perros de agua.");

        } else if (GrupoFCI.getSelectedItem().equals("Grupo IX: perros de compañía.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: Bichons y razas semejantes");
            SeccionFCI.addItem("Sección 2: Caniche");
            SeccionFCI.addItem("Sección 3: Perros belga de talla pequeña");
            SeccionFCI.addItem("Sección 4: Perros sin pelos");
            SeccionFCI.addItem("Sección 5: Perros tibetanos");
            SeccionFCI.addItem("Sección 6: Chihuahueno");
            SeccionFCI.addItem("Sección 7: Spaniels ingleses de compañía");
            SeccionFCI.addItem("Sección 8: Spaniels japoneses y pekineses");
            SeccionFCI.addItem("Sección 9: Spaniels continental enano y otro");
            SeccionFCI.addItem("Sección 10:  Kromfohrlande");
            SeccionFCI.addItem("Sección 11: Molosoides de talla pequeña");
        } else if (GrupoFCI.getSelectedItem().equals("Grupo X: perros lebreles.")) {
            SeccionFCI.removeAllItems();
            SeccionFCI.setSelectedItem(null);
            SeccionFCI.addItem("Sección 1: lebreles de pelo largo u ondulado");
            SeccionFCI.addItem("Sección 2: lebreles de pelo duro");
            SeccionFCI.addItem("Sección 3: lebreles de pelo corto");

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_GrupoFCIActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonConsultar;
    public javax.swing.JButton BotonEliminar;
    public javax.swing.JButton BotonInsertar;
    public javax.swing.JButton BotonLimpiar;
    public javax.swing.JButton BotonModificar;
    public javax.swing.JButton BotonSalir;
    public javax.swing.JButton BotonSerializar;
    public javax.swing.JComboBox<String> GrupoFCI;
    private javax.swing.JPanel PanelBaseDeDatos;
    private javax.swing.JPanel PanelBotones;
    private javax.swing.JPanel PanelDatos;
    public javax.swing.JComboBox<String> SeccionFCI;
    public javax.swing.JTable Tabla;
    public javax.swing.JTextField TextApariencia;
    public javax.swing.JTextField TextCola;
    public javax.swing.JTextField TextColor;
    public javax.swing.JTextField TextEspalda;
    public javax.swing.JTextField TextLomo;
    public javax.swing.JTextField TextPais;
    public javax.swing.JTextField TextPecho;
    public javax.swing.JTextField TextPelo;
    public javax.swing.JTextField TextRaza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
