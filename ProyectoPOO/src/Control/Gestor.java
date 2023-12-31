package Control;

import Modelo.Atracciones;
import Modelo.TiendaRestaurante;
import Vista.VistaAdmin;
import Vista.VistaBienvenida;
import Vista.VistaConsultar;
import Vista.VistaMapa;
import Vista.VistaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;

public class Gestor implements ActionListener {

    private VistaAdmin vistaAdmin;
    private VistaConsultar vistaConsulta;
    private VistaUsuario vistaUsuario;
    private VistaBienvenida vistaBienvenida;
    private VistaMapa vistaMapa;
    private ArrayList<Atracciones> misAtracciones = new ArrayList<>();
    private ArrayList<TiendaRestaurante> misTiendaRestaurante = new ArrayList<>();

    /**
     * Constructor de la clase Gestor. Inicializa las instancias de las vistas y
     * configura los listeners para los botones relevantes.
     */
    public Gestor() {
        // Inicialización de las instancias de las vistas
        this.vistaConsulta = new VistaConsultar();
        this.vistaBienvenida = new VistaBienvenida();
        this.vistaAdmin = new VistaAdmin();
        this.vistaUsuario = new VistaUsuario();
        this.vistaMapa = new VistaMapa();
       

        // Configuración de los listeners para los botones de las vistas
        this.vistaConsulta.btnConsultar.addActionListener(this);
        this.vistaBienvenida.btnAdmin.addActionListener(this);
        this.vistaBienvenida.btnUsuario.addActionListener(this);
        this.vistaAdmin.btnAgregar.addActionListener(this);
        this.vistaAdmin.btnModificar.addActionListener(this);
        this.vistaAdmin.btnEliminar.addActionListener(this);
        this.vistaAdmin.btnSeleccionar.addActionListener(this);
        this.vistaAdmin.btnVolver.addActionListener(this);
        this.vistaAdmin.btnSalir.addActionListener(this);
        this.vistaUsuario.btnSeleccionar.addActionListener(this);
        this.vistaUsuario.btnVolver.addActionListener(this);
        this.vistaUsuario.btnSalir.addActionListener(this);
        this.vistaUsuario.btnConsultar.addActionListener(this);
        this.vistaUsuario.btnMapa.addActionListener(this);
        this.vistaMapa.btnVolver.addActionListener(this);
        this.vistaConsulta.btnVolver.addActionListener(this);
        this.vistaUsuario.btnCalificar.addActionListener(this);
        // Inicialización y configuración inicial de las vistas
        iniciar();
        llenarCombo();
    }

    /**
     * Método que realiza la configuración inicial de la vistaBienvenida y
     * establece algunas propiedades visuales.
     */
    public void iniciar() {
        Properties objpropieadades = new Properties();
        // Establece el título de la vistaAdmin
        this.vistaAdmin.setTitle("Jaime Duque");
        cargaProperties(objpropieadades);
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vistaBienvenida.setLocationRelativeTo(null);

        // Deshabilita la capacidad de redimensionar la vistaBienvenida
        this.vistaBienvenida.setResizable(false);

        // Hace visible la vistaBienvenida
        this.vistaBienvenida.setVisible(true);
    }

    public void cargaProperties(Properties propiedades) {
        // Declaración de variables
        InputStream entrada = null;

        try {
            // Se abre un archivo de propiedades
            entrada = new FileInputStream("src/data/Atracciones.properties");

            // Se carga el archivo de propiedades en el objeto propiedades
            propiedades.load(entrada);

            // Se obtiene la cantidad de elementos a procesar
            int cantidad = Integer.parseInt(propiedades.getProperty("Cantidad"));

            // Bucle para procesar cada elemento
            for (int i = 1; i <= cantidad; i++) {
                String atraccionInfo = propiedades.getProperty("Atraccion" + i);

                if (atraccionInfo != null) {
                    String[] caracteristicasArray = atraccionInfo.split(", ");

                    // Verifica si hay suficientes elementos para crear un objeto PastaVO
                    if (caracteristicasArray.length >= 4) {
                        String nombrePasta = caracteristicasArray[0];

                        // Crea un objeto PastaVO y lo agrega a la lista
                        Atracciones agregarAtraccion = new Atracciones();
                        agregarAtraccion.setNombre(nombrePasta);
                        agregarAtraccion.setDescripcion(caracteristicasArray[1]);
                        agregarAtraccion.setUbicacion(caracteristicasArray[2]);
                        agregarAtraccion.setPrecio(Integer.parseInt(caracteristicasArray[3]));
                        misAtracciones.add(agregarAtraccion);
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Método privado utilizado para llenar el ComboBox de la vistaAdmin con
     * elementos de las listas misAtracciones y misTiendaRestaurante.
     */
    private void llenarCombo() {
        // Limpia los elementos existentes en el ComboBox
        vistaAdmin.comboxJaime.removeAllItems();
        vistaUsuario.comboxUsuarios.removeAllItems();

        // Agrega la opción nula o predeterminada al principio del ComboBox
        vistaAdmin.comboxJaime.addItem("Seleccionar");
        vistaUsuario.comboxUsuarios.addItem("Seleccionar");

        // Agrega elementos de misAtracciones al ComboBox
        for (Atracciones atraccion : misAtracciones) {
            vistaAdmin.comboxJaime.addItem(atraccion.getNombre());
            vistaUsuario.comboxUsuarios.addItem(atraccion.getNombre());
        }

        // Agrega elementos de misTiendaRestaurante al ComboBox
        for (TiendaRestaurante tiendaRestaurante : misTiendaRestaurante) {
            vistaAdmin.comboxJaime.addItem(tiendaRestaurante.getNombre());
            vistaUsuario.comboxUsuarios.addItem(tiendaRestaurante.getNombre());
        }
    }

    /**
     * Método privado utilizado para verificar si un nombre ya existe en el
     * ComboBox de la vistaAdmin.
     *
     * @param nombre El nombre a verificar.
     * @return true si el nombre ya existe en el ComboBox, false de lo
     * contrario.
     */
    private boolean nombreExistenteEnComboBox(String nombre) {
        int itemCount = vistaAdmin.comboxJaime.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (nombre.equals(vistaAdmin.comboxJaime.getItemAt(i))) {
                return true; // El nombre ya existe en el ComboBox
            }
        }
        return false; // El nombre no existe en el ComboBox
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Bloque condicional que verifica si el evento proviene del botón
         * "Admin" en la vistaBienvenida. En caso afirmativo, oculta la
         * vistaBienvenida y muestra la vistaAdmin, estableciendo su posición
         * relativa, deshabilitando la capacidad de redimensionar y haciéndola
         * visible.
         */
        //Funcionalidades Usuario
        if(e.getSource() == vistaUsuario.btnCalificar){
            String strCalificacion = vistaUsuario.txtCalificacion.getText();
            String nombre = vistaUsuario.txtNombre.getText();
            if (strCalificacion.isEmpty()) {
                // Muestra un mensaje de error si alguna casilla está vacía
                vistaUsuario.error("La calificación debe estar llena");
            }else{
                
                try{
            int calificacion = Integer.parseInt(strCalificacion);
            if(calificacion>=1 && calificacion<=5){
                         for (Atracciones atraccion : misAtracciones) {
                            if (atraccion.getNombre().equals(nombre)) {
                                    atraccion.setCalificacion(calificacion);
                                    vistaUsuario.msg("Su calificacion ha sido guardada");
                                    vistaUsuario.limpiar();
                                return; // Terminar el bucle ya que encontramos el objeto
                            }
                        }
            }else{
            vistaUsuario.error("Calificacion invalida");
            }
            }catch(NumberFormatException ex){
                vistaUsuario.error("Calificacion invalida");
            }
            }
            
        }
        
        if (e.getSource() == vistaConsulta.btnConsultar){
            ArrayList<String> filtroBusqueda = new ArrayList<>(); 
            String nombre = vistaConsulta.txtNombreConsulta.getText();
            String ubicacion = vistaConsulta.txtUbicacionConsulta.getText();
            
            if(nombre.isEmpty() && ubicacion.isEmpty()){
                vistaConsulta.error("No es valida una consulta sin parametros");
            }else  if(!nombre.isEmpty() && !ubicacion.isEmpty()){
                for(Atracciones atraccion : misAtracciones){
                    if(atraccion.getNombre().equals(nombre)){
                        vistaConsulta.txtNombreResult.setText(atraccion.getNombre());
                        vistaConsulta.txtDescripcionResult.setText(atraccion.getDescripcion());
                        vistaConsulta.txtUbicacionResult.setText(atraccion.getUbicacion());
                        vistaConsulta.txtPrecioResult.setText(Integer.toString(atraccion.getPrecio())); 
                    }
                }
                   for(TiendaRestaurante tiendaRestaurante : misTiendaRestaurante){
                    if(tiendaRestaurante.getNombre().equals(nombre)){
                        vistaConsulta.txtNombreResult.setText(tiendaRestaurante.getNombre());
                        vistaConsulta.txtDescripcionResult.setText(tiendaRestaurante.getDescripcion());
                        vistaConsulta.txtUbicacionResult.setText(tiendaRestaurante.getUbicacion());
                        vistaConsulta.txtPrecioResult.setText(" "); 
                    }
                }
                for(Atracciones atraccion : misAtracciones){
                    if(atraccion.getUbicacion().equals(ubicacion)){
                        filtroBusqueda.add(atraccion.getNombre());
                    }
                }
                   for(TiendaRestaurante tiendaRestaurante : misTiendaRestaurante){
                    if(tiendaRestaurante.getUbicacion().equals(ubicacion)){
                        filtroBusqueda.add(tiendaRestaurante.getNombre());
                    }
                }
                 String[][] data= new String  [filtroBusqueda.size()][1];
                 for(int i = 0; i<filtroBusqueda.size(); i++){
                     data[i][0] = filtroBusqueda.get(i);  
                 }
                 vistaConsulta.tablaUbicaciones(data);
            }else if(!nombre.isEmpty() || ubicacion.isEmpty()){
                
                for(Atracciones atraccion : misAtracciones){
                    if(atraccion.getNombre().equals(nombre)){
                        vistaConsulta.txtNombreResult.setText(atraccion.getNombre());
                        vistaConsulta.txtDescripcionResult.setText(atraccion.getDescripcion());
                        vistaConsulta.txtUbicacionResult.setText(atraccion.getUbicacion());
                        vistaConsulta.txtPrecioResult.setText(Integer.toString(atraccion.getPrecio())); 
                    }
                }
                   for(TiendaRestaurante tiendaRestaurante : misTiendaRestaurante){
                    if(tiendaRestaurante.getNombre().equals(nombre)){
                        vistaConsulta.txtNombreResult.setText(tiendaRestaurante.getNombre());
                        vistaConsulta.txtDescripcionResult.setText(tiendaRestaurante.getDescripcion());
                        vistaConsulta.txtUbicacionResult.setText(tiendaRestaurante.getUbicacion());
                        vistaConsulta.txtPrecioResult.setText(" "); 
                    }
                }
            }else if(nombre.isEmpty() || !ubicacion.isEmpty()){
                for(Atracciones atraccion : misAtracciones){
                    if(atraccion.getUbicacion().equals(ubicacion)){
                        filtroBusqueda.add(atraccion.getNombre());
                    }
                }
                   for(TiendaRestaurante tiendaRestaurante : misTiendaRestaurante){
                    if(tiendaRestaurante.getUbicacion().equals(ubicacion)){
                        filtroBusqueda.add(tiendaRestaurante.getNombre());
                    }
                }
                 String[][] data= new String  [filtroBusqueda.size()][1];
                 for(int i = 0; i<filtroBusqueda.size(); i++){
                     data[i][0] = filtroBusqueda.get(i);  
                 }
                 vistaConsulta.tablaUbicaciones(data);
            }
                   
        }
        
        //Botón volver de consulta
        if (e.getSource() == vistaConsulta.btnVolver){
            vistaUsuario.setVisible(true);
            vistaConsulta.dispose();
        }
        
        //Boton mostrar mapa
        if (e.getSource() == vistaUsuario.btnMapa){
            vistaMapa.setLocationRelativeTo(null);
            vistaMapa.setVisible(true);   
        }
        
        //Boton volver del mapa
        if(e.getSource() == vistaMapa.btnVolver){
            vistaMapa.dispose();
            vistaUsuario.setVisible(true);
        }
        
        //Boton volver a inicio
        if (e.getSource() == vistaUsuario.btnVolver){
            vistaUsuario.dispose();
            vistaUsuario.limpiar();
            vistaBienvenida.setVisible(true);
        }
        
        //Boton Salir
        if(e.getSource() == vistaUsuario.btnSalir){
            System.exit(0);
        }
        
        if (e.getSource() == vistaAdmin.btnSeleccionar) {
            // Obtiene el nombre seleccionado del ComboBox
            String nombreSeleccionado = (String) vistaAdmin.comboxJaime.getSelectedItem();
            

            // Desactiva los botones de selección (Atracciones y TiendaRestaurante)
            vistaAdmin.btnAtracciones.setEnabled(false);
            vistaAdmin.btnTiendaRestaurante.setEnabled(false);

            // Buscar la atracción en el ArrayList por su nombre
            for (Atracciones atraccion : misAtracciones) {
                if (atraccion.getNombre().equals(nombreSeleccionado)) {
                    // Actualizar los valores de la atracción seleccionada en las casillas de texto
                    vistaAdmin.txtNombre.setText(atraccion.getNombre());
                    vistaAdmin.txtDescription.setText(atraccion.getDescripcion());
                    vistaAdmin.txtUbicacion.setText(atraccion.getUbicacion());
                    vistaAdmin.txtPrecio.setText(Integer.toString(atraccion.getPrecio()));
                    vistaAdmin.btnAtracciones.setSelected(true);
                    vistaAdmin.btnTiendaRestaurante.setSelected(false);
                    return; // Terminar el bucle ya que encontramos el objeto
                }
            }

            // Buscar la tienda/restaurante en el ArrayList por su nombre
            for (TiendaRestaurante tiendaRestaurante : misTiendaRestaurante) {
                if (tiendaRestaurante.getNombre().equals(nombreSeleccionado)) {
                    // Actualizar los valores de la tienda/restaurante seleccionada en las casillas de texto
                    vistaAdmin.txtNombre.setText(tiendaRestaurante.getNombre());
                    vistaAdmin.txtDescription.setText(tiendaRestaurante.getDescripcion());
                    vistaAdmin.txtUbicacion.setText(tiendaRestaurante.getUbicacion());
                    vistaAdmin.btnAtracciones.setSelected(false);
                    vistaAdmin.btnTiendaRestaurante.setSelected(true);
                    return; // Terminar el bucle ya que encontramos el objeto
                }
            }
        }

        if (e.getSource() == vistaBienvenida.btnAdmin) {
            // Oculta la vistaBienvenida
            this.vistaBienvenida.setVisible(false);

            // Establece la posición relativa de la vistaAdmin al centro de la pantalla
            this.vistaAdmin.setLocationRelativeTo(null);

            // Deshabilita la capacidad de redimensionar la vistaAdmin
            this.vistaAdmin.setResizable(false);

            // Hace visible la vistaAdmin
            this.vistaAdmin.setVisible(true);
        }

        //FUNCIONALIDADES DEL ADMIN
        if (e.getSource() == vistaAdmin.btnSalir) {
            System.exit(0);
        }

        /**
         * Bloque condicional que verifica si el evento proviene del botón
         * "Agregar" en la vistaAdmin. En caso afirmativo, realiza las
         * siguientes operaciones: - Crea instancias de las clases Atracciones y
         * TiendaRestaurante. - Obtiene los valores de las casillas de texto. -
         * Verifica si alguna casilla está vacía y muestra un mensaje de error
         * si es el caso. - Verifica si alguno de los botones de selección
         * (Atracciones o TiendaRestaurante) está activado y muestra un mensaje
         * de error si ninguno está seleccionado. - Verifica si el nombre ya
         * existe en el ComboBox y muestra un mensaje de error si es el caso. -
         * Si todo es válido, agrega una Atracción o TiendaRestaurante al
         * ArrayList correspondiente. - Actualiza el ComboBox y limpia las
         * casillas de texto.
         */
        if (e.getSource() == vistaAdmin.btnAgregar) {
            // Crea instancias de las clases Atracciones y TiendaRestaurante
            Atracciones agregarAtraccion = new Atracciones();
            TiendaRestaurante agregarTiendaRestaurante = new TiendaRestaurante();

            // Obtiene valores de las casillas de texto
            String nombre = vistaAdmin.txtNombre.getText();
            String description = vistaAdmin.txtDescription.getText();
            String ubicacion = vistaAdmin.txtUbicacion.getText();
            String strPrecio = vistaAdmin.txtPrecio.getText();
            int precio = 0;

            // Verifica si alguna casilla está vacía
            if (nombre.isEmpty() || description.isEmpty() || ubicacion.isEmpty()) {
                // Muestra un mensaje de error si alguna casilla está vacía
                vistaAdmin.error("Todos los campos deben estar llenos");
            } else if (!vistaAdmin.btnAtracciones.isSelected() && !vistaAdmin.btnTiendaRestaurante.isSelected()) {
                // Muestra un mensaje de error si ninguno de los dos botones está seleccionado
                vistaAdmin.error("Seleccione una opción");
            }

            // Verifica si el nombre ya está en el ComboBox
            if (!nombreExistenteEnComboBox(nombre)) {
                if (vistaAdmin.btnAtracciones.isSelected()) {
                    try {
                        // Intenta convertir el String a un número
                        precio = Integer.parseInt(strPrecio);
                        agregarAtraccion.setNombre(nombre);
                        agregarAtraccion.setDescripcion(description);
                        agregarAtraccion.setUbicacion(ubicacion);
                        agregarAtraccion.setPrecio(precio);
                        misAtracciones.add(agregarAtraccion);
                    } catch (NumberFormatException ex) {
                        vistaAdmin.error("No es un número válido");
                    }
                } else if (vistaAdmin.btnTiendaRestaurante.isSelected()) {
                    agregarTiendaRestaurante.setNombre(nombre);
                    agregarTiendaRestaurante.setDescripcion(description);
                    agregarTiendaRestaurante.setUbicacion(ubicacion);
                    misTiendaRestaurante.add(agregarTiendaRestaurante);
                }

                // Actualiza el ComboBox y limpia las casillas de texto
                llenarCombo();
                vistaAdmin.limpiar();
            } else {
                // Muestra un mensaje de error si el nombre ya existe en el ComboBox
                vistaAdmin.error("Ya existe");
            }
        }

        /**
         * Bloque condicional que verifica si el evento proviene del botón
         * "Seleccionar" en la vistaAdmin. En caso afirmativo, realiza las
         * siguientes operaciones: - Obtiene el nombre seleccionado del
         * ComboBox. - Desactiva los botones de selección (Atracciones y
         * TiendaRestaurante). - Verifica si la opción de Atracciones está
         * seleccionada y actualiza los valores de la atracción seleccionada en
         * las casillas de texto. - Verifica si la opción de TiendaRestaurante
         * está seleccionada y actualiza los valores de la tienda/restaurante
         * seleccionada en las casillas de texto.
         */
        //Funcionalidades Usuario
        if (e.getSource() == vistaUsuario.btnConsultar){
            vistaConsulta.setLocationRelativeTo(null);
            vistaConsulta.setVisible(true);
            vistaConsulta.txtNombreResult.setEditable(false);
            vistaConsulta.txtDescripcionResult.setEditable(false);
            vistaConsulta.txtUbicacionResult.setEditable(false);
            vistaConsulta.txtPrecioResult.setEditable(false);
            vistaUsuario.dispose();
        }
        
        if (e.getSource() == vistaUsuario.btnSeleccionar) {
            String nombreSeleccionado = (String) vistaUsuario.comboxUsuarios.getSelectedItem();
            vistaUsuario.setLocationRelativeTo(null);
            vistaUsuario.txtNombre.setEnabled(false);
            for (Atracciones atraccion : misAtracciones) {
                if (atraccion.getNombre().equals(nombreSeleccionado)) {
                    // Actualizar los valores de la atracción seleccionada en las casillas de texto
                    vistaUsuario.txtNombre.setText(atraccion.getNombre());
                    vistaUsuario.txtCalificacion.setText(Integer.toString(atraccion.getCalificacion()));
                    return; // Terminar el bucle ya que encontramos el objeto
                }
            }
            // Buscar la tienda/restaurante en el ArrayList por su nombre
            for (TiendaRestaurante tiendaRestaurante : misTiendaRestaurante) {
                if (tiendaRestaurante.getNombre().equals(nombreSeleccionado)) {
                    // Actualizar los valores de la tienda/restaurante seleccionada en las casillas de texto
                    vistaUsuario.txtNombre.setText(tiendaRestaurante.getNombre());
                    vistaUsuario.txtCalificacion.setText(Integer.toString(tiendaRestaurante.getCalificacion()));
                    return; // Terminar el bucle ya que encontramos el objeto
                }
            }
        }

        /**
         * Bloque condicional que verifica si el evento proviene del botón
         * "Modificar" en la vistaAdmin. En caso afirmativo, realiza las
         * siguientes operaciones: - Obtiene el nombre seleccionado del
         * ComboBox. - Verifica que se haya seleccionado una opción del
         * ComboBox. - Obtiene los valores de las casillas de texto. - Verifica
         * si alguna casilla está vacía y muestra un mensaje de error en caso
         * afirmativo. - Verifica si se ha seleccionado alguna opción
         * (Atracciones o TiendaRestaurante) y muestra un mensaje de error en
         * caso negativo. - Si se selecciona Atracciones, busca la atracción en
         * el ArrayList por su nombre y actualiza sus valores. - Si se
         * selecciona TiendaRestaurante, busca la tienda/restaurante en el
         * ArrayList por su nombre y actualiza sus valores. - Muestra un mensaje
         * de modificación exitosa y limpia las casillas de texto. - Actualiza
         * el ComboBox con los datos actualizados. - Si no se ha seleccionado
         * ninguna opción en el ComboBox, muestra un mensaje de error.
         */
        if (e.getSource() == vistaAdmin.btnModificar) {
            // Obtener el nombre seleccionado del ComboBox
            String nombreSeleccionado = (String) vistaAdmin.comboxJaime.getSelectedItem();

            if (!nombreSeleccionado.isEmpty()) {
                // Obtener valores de las casillas de texto
                String nombre = vistaAdmin.txtNombre.getText();
                String description = vistaAdmin.txtDescription.getText();
                String ubicacion = vistaAdmin.txtUbicacion.getText();
                String strPrecio = vistaAdmin.txtPrecio.getText();
                int precio = 0;

                // Verificar si alguna casilla está vacía
                if (nombre.isEmpty() || description.isEmpty() || ubicacion.isEmpty()) {
                    // Mostrar un mensaje de error si alguna casilla está vacía
                    vistaAdmin.error("Todos los campos deben estar llenos");
                } else if (!vistaAdmin.btnAtracciones.isSelected() && !vistaAdmin.btnTiendaRestaurante.isSelected()) {
                    // Mostrar un mensaje de error si ninguno de los dos botones está seleccionado
                    vistaAdmin.error("Seleccione una opción");
                } else {
                    if (vistaAdmin.btnAtracciones.isSelected()) {
                        // Buscar la atracción en el ArrayList por su nombre
                        for (Atracciones atraccion : misAtracciones) {
                            if (atraccion.getNombre().equals(nombreSeleccionado)) {
                                try {
                                    // Intenta convertir el String a un número
                                    precio = Integer.parseInt(strPrecio);
                                    // Actualizar los valores de la atracción seleccionada
                                    atraccion.setNombre(nombre);
                                    atraccion.setDescripcion(description);
                                    atraccion.setUbicacion(ubicacion);
                                    atraccion.setPrecio(precio);
                                    vistaAdmin.msg("Modificado");
                                    vistaAdmin.limpiar();
                                } catch (NumberFormatException ex) {
                                    vistaAdmin.error("No es un número válido");
                                    return;
                                }
                                return; // Terminar el bucle ya que encontramos el objeto
                            }
                        }
                    } else if (vistaAdmin.btnTiendaRestaurante.isSelected()) {
                        // Buscar la tienda/restaurante en el ArrayList por su nombre
                        for (TiendaRestaurante tiendaRestaurante : misTiendaRestaurante) {
                            if (tiendaRestaurante.getNombre().equals(nombreSeleccionado)) {
                                // Actualizar los valores de la tienda/restaurante seleccionada
                                tiendaRestaurante.setNombre(nombre);
                                tiendaRestaurante.setDescripcion(description);
                                tiendaRestaurante.setUbicacion(ubicacion);
                                vistaAdmin.msg("Modificado");
                                vistaAdmin.limpiar();
                                return; // Terminar el bucle ya que encontramos el objeto
                            }
                        }
                    }

                    // Actualizar el ComboBox con los datos actualizados
                    llenarCombo();
                }
            } else {
                // Mostrar un mensaje de error si no se ha seleccionado ninguna opción en el ComboBox
                vistaAdmin.error("Seleccione una opción del ComboBox para modificar");
            }
        }
        /**
         * Bloque condicional que verifica si el evento proviene del botón
         * "Eliminar" en la vistaAdmin. En caso afirmativo, realiza las
         * siguientes operaciones: - Obtiene el nombre seleccionado del
         * ComboBox. - Verifica que se haya seleccionado una opción del
         * ComboBox. - Si se selecciona Atracciones, busca la atracción en el
         * ArrayList por su nombre y la elimina. - Si se selecciona
         * TiendaRestaurante, busca la tienda/restaurante en el ArrayList por su
         * nombre y la elimina. - Muestra un mensaje de eliminación exitosa y
         * limpia las casillas de texto. - Borra el nombre del elemento
         * eliminado del ComboBox. - Si no se ha seleccionado ninguna opción en
         * el ComboBox, muestra un mensaje de error.
         */
        if (e.getSource() == vistaBienvenida.btnUsuario) {
            vistaUsuario.setLocationRelativeTo(null);
            vistaBienvenida.setVisible(false);
            vistaUsuario.setVisible(true);
        }

        if (e.getSource() == vistaAdmin.btnEliminar) {
            // Obtener el nombre seleccionado del ComboBox
            String nombreSeleccionado = (String) vistaAdmin.comboxJaime.getSelectedItem();
            // Verificar que se haya seleccionado una opción del ComboBox
            if (!nombreSeleccionado.isEmpty()) {
                if (vistaAdmin.btnAtracciones.isSelected()) {
                    // Buscar la atracción en el ArrayList por su nombre y eliminarla
                    for (Atracciones atraccion : misAtracciones) {
                        if (atraccion.getNombre().equals(nombreSeleccionado)) {
                            misAtracciones.remove(atraccion);
                            vistaAdmin.msg("Atracción Eliminada");
                            vistaAdmin.limpiar();
                              llenarCombo();
                            break; // Terminar el bucle ya que encontramos el objeto
                        }
                    }
                } else if (vistaAdmin.btnTiendaRestaurante.isSelected()) {
                    // Buscar la tienda/restaurante en el ArrayList por su nombre y eliminarla
                    for (TiendaRestaurante tiendaRestaurante : misTiendaRestaurante) {
                        if (tiendaRestaurante.getNombre().equals(nombreSeleccionado)) {
                            misTiendaRestaurante.remove(tiendaRestaurante);
                            vistaAdmin.msg("Tienda/Restaurante Eliminado/a");
                            vistaAdmin.limpiar();
                              llenarCombo();
                            break; // Terminar el bucle ya que encontramos el objeto
                        }
                    }
                }
               llenarCombo();
            } else {
                vistaAdmin.error("Seleccione una opción");
            }
        }

        /**
         * Bloque condicional que verifica si el evento proviene del botón
         * "Volver" en la vistaAdmin. En caso afirmativo, realiza las siguientes
         * operaciones: - Oculta la ventana de administración (vistaAdmin). -
         * Muestra la ventana de bienvenida (vistaBienvenida).
         */
        if (e.getSource() == vistaAdmin.btnVolver) {
            this.vistaAdmin.setVisible(false);
            this.vistaBienvenida.setVisible(true);
        }

        //FUNCIONALIDADES DEL USUARIO
    }
}
