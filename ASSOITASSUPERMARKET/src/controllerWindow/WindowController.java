package controllerWindow;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import exceptions.insufficientQuantityException;
import exceptions.noMatchesException;
import exceptions.repeatedCustomerException;
import exceptions.unavaiableIdException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import threads.ConfirmationSoundThread;
import threads.MusicThread;
import threads.SoundThread;

public class WindowController implements Initializable{
	
	private SuperMarketApp sma = new SuperMarketApp();
	private MediaPlayer mediaPlayer;
	
	
	@FXML
	private MenuItem registrarCliente;
	
	@FXML
	private MenuItem registrarClienteLeal;
	
	@FXML
	private MenuItem registrarUnidad;
	
	@FXML
	private MenuItem registrarPeso;
	
	@FXML
	private MenuItem registrarInmobiliariaPrivada;
	
	@FXML
	private MenuItem registrarInmobiliariaPublica;
	
	@FXML
	private TextField buscarProducto;
	
	@FXML
	private Button buscar;
	
	@FXML
	private Button salir;
	
	@FXML
	private Pane productsPane;
	
	@FXML
	private MenuItem mostrarCliente;
	
	@FXML
	private MenuItem mostrarClienteLeal;
	
	@FXML
	private MenuItem mostrarInmoviliarias;
	
	@FXML
	private MenuItem mostrarTrabajadores;
	
	@FXML
	private MenuItem eliminarInmoviliaria;
	
	@FXML
	private MenuItem registrarGerente;
	
	@FXML
	private MenuItem registrarAdministrador;
	
	@FXML
	private MenuItem eliminarTrabajador;
	
	@FXML
	private MenuItem actualizarUnidad;
	
	@FXML
	private MenuItem actualizarPeso;
	
	@FXML
	private MenuItem actualizarCliente;
	
	@FXML
	private MenuItem actualizarClienteLeal;
	
	@FXML
	private MenuItem actualizarGerente;
	
	@FXML
	private MenuItem actualizarAdministrador;
	
	@FXML
	private MenuItem actualizarPublica;
	
	@FXML
	private MenuItem actualizarPrivada;
	
	@FXML
	private MenuItem eliminarUnidad;
	
	@FXML
	private MenuItem eliminarPeso;
	
	@FXML
	private MenuItem buscarTrabajador;
	
	@FXML
	private MenuItem buscarInmobiliaria;
	
	@FXML
	private MenuItem ordenarTrabajadorId;
	
	@FXML
	private MenuItem ordenarTrabajadorNombre;
	
	@FXML
	private MenuItem ordenarInmobiliariaCantidad;
	
	@FXML
	private MenuItem ordenarInmobiliariaId;
	
	@FXML
	private MenuItem ordenarProductosId;
	
	@FXML
	private MenuItem ordenarProductosNombre;
	
	@FXML
	private Button menu;
	
	@FXML
	private Button imprimir;
	
	private GraphicsContext gc;
	
	private GridPane gp;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		sma.loadEverythig();
		MusicThread mt = new MusicThread(this);
		mt.start();
		ActionEvent av = new ActionEvent();
		mostrarProductos(av);
		
	}
	

	
	public void done(ActionEvent av) {
		Label l = new Label("Se ha realizado exitosamente");
    	GridPane g = new GridPane();
    	Button b = new Button();
    	b.setText("OK");
    	g.setAlignment(Pos.CENTER);
    	
    	StackPane done = new StackPane();
    		        	
    	Scene doneScene = new Scene(done, 300, 100);
        Stage doneWindow = new Stage();
        
        b.setOnAction(errorEvent -> {
        	doneWindow.close();
        });
        
        done.getChildren().add(g);
    	g.add(l, 0, 0);
    	g.add(b, 0, 1);
        
        
    	doneWindow.setTitle(" ");
    	doneWindow.setScene(doneScene);

    	doneWindow.show();
    	ConfirmationSoundThread cst = new ConfirmationSoundThread(this);
    	cst.start();
	}
	
	public void startNotification() {
		String musicFile = "./marketRecords/not.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.1);
		mediaPlayer.play();
	}
	
	public void startMusic() {
		String musicFile = "./marketRecords/music.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.03);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		
	}
	
	public void startExit() {
		String musicFile = "./marketRecords/exit.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.03);
		mediaPlayer.play();
		
	}
	
	public void buscar(ActionEvent av) {
		productsPane.getChildren().clear();
		try {
			gp = new GridPane();
			gp.setPadding(new Insets(100));
			productsPane.getChildren().add(gp);
			String aux = buscarProducto.getText();
			if(sma.searchGeneralProducts(aux) instanceof WeightProduct){
				WeightProduct wp = (WeightProduct) sma.searchGeneralProducts(aux);
				Label l = new Label(wp.getName() + "\n $" + wp.getPrice() + "\n Cantidad restante: " + wp.getWeight());
				Font font = new Font(20);
				l.setFont(font);
				Button b = new Button();
				b.setText("Agregar");
				gp.add(b, 1, 0);
				gp.add(l, 0, 0);
			}else {
				UnityProduct wp = (UnityProduct) sma.searchGeneralProducts(aux);
				Label l = new Label(wp.getName() + "\n $" + wp.getPrice() + "\n Cantidad restante: " + wp.getQuantity());
				Font font = new Font(20);
				l.setFont(font);
				Button b = new Button();
				b.setText("Agregar");
				gp.add(b, 1, 0);
				gp.add(l, 0, 0);
			}
		}catch (noMatchesException e) {
			productsPane.getChildren().clear();
		}
	}
	
	public void mostrarProductos(ActionEvent av) {
		productsPane.getChildren().clear();
		
		try {
			gp = new GridPane();
			gp.setPadding(new Insets(100));
			gp.setHgap(100);
			productsPane.getChildren().add(gp);
			UnityProduct up = sma.getInventory().getFirstUnity();
			WeightProduct wp = sma.getInventory().getFirstWeight();
			int i = 0;
			int j = 0;
			while(up!=null) {
				Label l = new Label(up.getName() + " (ID " + up.getId() + ")\n $" + up.getPrice() + "\n Cantidad restante: " + up.getQuantity() + "\n ");
				Font font = new Font(20);
				l.setFont(font);
				String aux = up.getId();
				
				Button b = new Button();
				b.setText("Agregar");
				b.setOnAction(add -> {
					agregarProductos(aux, av);
				});
				
				gp.add(b, 1, i);
				gp.add(l, 0, i);
				
				up = up.getNext();
				i++;
			}
			while(wp!=null) {
				Label l = new Label(wp.getName()+ " (ID " + wp.getId() + ")\n $" + wp.getPrice() + "\n Cantidad restante: " + wp.getWeight() + "kg.\n ");
				Font font = new Font(20);
				l.setFont(font);
				String aux = wp.getId();
				
				Button b = new Button();
				b.setText("Agregar");
				b.setOnAction(add -> {
					agregarProductos(aux, av);
				});
				
				gp.add(b, 3, j);
				gp.add(l, 2, j);
				
				wp = wp.getNext();
				j++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void mostrarMenu(ActionEvent av) {
		mostrarProductos(av);
	}
	
	
	public void agregarProductos(String aux, ActionEvent av) {
		if(sma.getCashRegister().getInvoiceRoot()== null) {
			
			Label req = new Label("Cantidad solicitada");
			Label dat = new Label("Fecha");
			Label pay = new Label("Tipo de pago");
			Label fac = new Label("Numero de factura");
			Label cli = new Label("ID del Cliente");
			
			
			TextField requiredQuantity = new TextField();
			requiredQuantity.setMaxWidth(200);
			TextField date = new TextField();
			date.setMaxWidth(200);
			TextField paymentType = new TextField();
			paymentType.setMaxWidth(200);
			TextField factureNumber = new TextField();
			factureNumber.setMaxWidth(200);
			TextField clientId = new TextField();
			clientId.setMaxWidth(200);
			
			Button b = new Button();
			b.setText("Aceptar");
			b.setOnAction(add -> {
				try {
					sma.createInvoice(aux, requiredQuantity.getText(), date.getText(), paymentType.getText(), factureNumber.getText(), clientId.getText());
					done(av);
				} catch (noMatchesException e) {
		        	//SE CREA UNA VENTANA QUE INDICA QUE EL PRODUCTO A AGREGAR NO EXISTE
		        	Label errorLabel = new Label("El producto no existe");
		        	GridPane errorGrid = new GridPane();
		        	Button errorButton = new Button();
		        	errorButton.setText("OK");
		        	errorGrid.setAlignment(Pos.CENTER);
		        	
		        	StackPane error = new StackPane();
		        		        	
		        	Scene errorScene = new Scene(error, 300, 100);
		            Stage errorWindow = new Stage();
		            
		            errorButton.setOnAction(errorEvent -> {
		            	errorWindow.close();
		            });
		            
		            error.getChildren().add(errorGrid);
		        	errorGrid.add(errorLabel, 0, 0);
		        	errorGrid.add(errorButton, 0, 1);
		            
		            
		        	errorWindow.setTitle("Error.");
		        	errorWindow.setScene(errorScene);

		        	errorWindow.show();
		        }catch (insufficientQuantityException e) {
		        	//SE CREA UNA VENTANA QUE INDICA QUE EL PRODUCTO A AGREGAR NO TIENE SUFICIENTES CANTIDADES
		        	Label errorLabel = new Label("No hay suficiente cantidad del producto");
		        	GridPane errorGrid = new GridPane();
		        	Button errorButton = new Button();
		        	errorButton.setText("OK");
		        	errorGrid.setAlignment(Pos.CENTER);
		        	
		        	StackPane error = new StackPane();
		        		        	
		        	Scene errorScene = new Scene(error, 300, 100);
		            Stage errorWindow = new Stage();
		            
		            errorButton.setOnAction(errorEvent -> {
		            	errorWindow.close();
		            });
		            
		            error.getChildren().add(errorGrid);
		        	errorGrid.add(errorLabel, 0, 0);
		        	errorGrid.add(errorButton, 0, 1);
		            
		            
		        	errorWindow.setTitle("Error.");
		        	errorWindow.setScene(errorScene);

		        	errorWindow.show();
		        }catch (NullPointerException e) {
		        	//SE CREA UNA VENTANA QUE INDICA QUE EL CLIENTE INGRESADO NO EXISTE
		        	Label errorLabel = new Label("No esta registrado el cliente");
		        	GridPane errorGrid = new GridPane();
		        	Button errorButton = new Button();
		        	errorButton.setText("OK");
		        	errorGrid.setAlignment(Pos.CENTER);
		        	
		        	StackPane error = new StackPane();
		        		        	
		        	Scene errorScene = new Scene(error, 300, 100);
		            Stage errorWindow = new Stage();
		            
		            errorButton.setOnAction(errorEvent -> {
		            	errorWindow.close();
		            });
		            
		            error.getChildren().add(errorGrid);
		        	errorGrid.add(errorLabel, 0, 0);
		        	errorGrid.add(errorButton, 0, 1);
		            
		            
		        	errorWindow.setTitle("Error.");
		        	errorWindow.setScene(errorScene);

		        	errorWindow.show();
		        }catch (Exception e) {
		        	e.printStackTrace();
		        }
			});
			GridPane grid = new GridPane();
			grid.setHgap(20);
			grid.setVgap(20);
			grid.setPadding(new Insets(120));
			
			StackPane secondaryLayout = new StackPane();
			Scene secondScene = new Scene(secondaryLayout, 600, 300);
			Stage newWindow = new Stage();
			
			secondaryLayout.getChildren().add(grid);
			
			grid.add(req, 0, 0);
			grid.add(requiredQuantity, 1, 0);
			grid.add(dat, 0, 1);
			grid.add(date, 1, 1);
			grid.add(pay, 0, 2);
			grid.add(paymentType, 1, 2);
			grid.add(fac, 0, 3);
			grid.add(factureNumber, 1, 3);
			grid.add(cli, 0, 4);
			grid.add(clientId, 1, 4);
			grid.add(b, 0, 5);
			
			newWindow.setTitle("Crear Factura");
	        newWindow.setScene(secondScene);

	        newWindow.show();
		}else {
			try {
				sma.addMoreProductsToTheInvoice(sma.getCashRegister().getInvoiceRoot().getFactureNumber(), aux, 2);
			}catch (insufficientQuantityException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE EL PRODUCTO A AGREGAR NO TIENE SUFICIENTES CANTIDADES
	        	Label errorLabel = new Label("No hay suficiente cantidad del producto");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
		}
	}
	
	
	public void mostrarTrabajadores() {
		ArrayList<Worker> trabajadores = sma.getWorkers();
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 300);
		Stage newWindow = new Stage();
		
		GridPane grid = new GridPane();
		grid.setVgap(30);
		
		secondaryLayout.getChildren().add(grid);
	
		for(int i=0;i<trabajadores.size(); i++) {
			Label l = new Label(trabajadores.toString());
			grid.add(l, 0, i);
		}
		
		newWindow.setTitle("Mostrar Trabajadores");
        newWindow.setScene(secondScene);
        newWindow.show();
	}
	
	
	public void mostrarInmoviliarias() {
		ArrayList<Realstate> inmoviliarias = sma.getRealStates();
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 300);
		Stage newWindow = new Stage();
		
		GridPane grid = new GridPane();
		grid.setVgap(30);
		
		secondaryLayout.getChildren().add(grid);
	
		for(int i=0;i<inmoviliarias.size(); i++) {
			Label l = new Label(inmoviliarias.toString());
			grid.add(l, 0, i);
		}
		
		newWindow.setTitle("Mostrar Inmoviliarias");
        newWindow.setScene(secondScene);
        newWindow.show();
	}
	
	
	public void pintarCliente(ActionEvent av) {
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 300);
		Stage newWindow = new Stage();
		
		GridPane searchGrid = new GridPane();
		searchGrid.setAlignment(Pos.CENTER);
		Label client = new Label("Ingrese el ID del cliente");
		TextField searchC = new TextField();
		Button search = new Button();
		search.setText("Buscar");
		
		search.setOnAction(searchEvent -> {
			String s = sma.searchCurrentClient(searchC.getText());
			client.setText(s);
		});
		
		secondaryLayout.getChildren().add(searchGrid);
		searchGrid.add(client, 0, 0);
		searchGrid.add(searchC, 0, 1);
		searchGrid.add(search, 0, 2);
		
		newWindow.setTitle("Mostrar Cliente");
        newWindow.setScene(secondScene);
        newWindow.show();
	}
	
	
	
	public void pintarClienteLeal(ActionEvent av) {
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 300);
		Stage newWindow = new Stage();
		
		GridPane searchGrid = new GridPane();
		searchGrid.setAlignment(Pos.CENTER);
		Label client = new Label("Ingrese el ID del cliente leal");
		TextField searchC = new TextField();
		Button search = new Button();
		search.setText("Buscar");
		
		search.setOnAction(searchEvent -> {
			String s = sma.searchLoyalClient(searchC.getText());
			client.setText(s);
		});
		
		secondaryLayout.getChildren().add(searchGrid);
		searchGrid.add(client, 0, 0);
		searchGrid.add(searchC, 0, 1);
		searchGrid.add(search, 0, 2);
		
		
		newWindow.setTitle("Mostrar Cliente");
        newWindow.setScene(secondScene);
        newWindow.show();
	}
	
	
	
	public void registrarCliente(ActionEvent av) {
		Label nom = new Label("Nombre");
		
		Label ide = new Label("ID");
		
		Label eda = new Label("Edad");
		
		Label ema = new Label("Email");
		
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField id = new TextField();
		id.setMaxWidth(200);
				
		TextField age = new TextField();
		age.setMaxWidth(200);
				
		TextField email = new TextField();
		email.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR CLIENTE
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.addCurrentClient(id.getText(), name.getText(), age.getText(), email.getText());
	            done(av);
	        } catch (repeatedCustomerException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE EL CLIENTE ESTA REPETIDO
	        	Label errorLabel = new Label("El Cliente ya se encuentra registrado");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 300);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(nom, 0, 0);
		grid.add(name, 1, 0);
		grid.add(ide, 0, 1);
		grid.add(id, 1, 1);
		grid.add(eda, 0, 2);
		grid.add(age, 1, 2);
		grid.add(ema, 0, 3);
		grid.add(email, 1, 3);
		grid.add(aceptar, 0, 4);
		grid.add(cancelar, 1, 4);
		

        newWindow.setTitle("Registrar Cliente");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void actualizarCliente(ActionEvent av) {
		Label ide = new Label("ID");
		
		Label nam = new Label("Nombre");
		
		Label eda = new Label("Edad");
		
		Label ema = new Label("E-mail");
		
	
		TextField id = new TextField();
		id.setMaxWidth(200);
		
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField age = new TextField();
		age.setMaxWidth(200);
				
		TextField email = new TextField();
		email.setMaxWidth(200);
		
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE ACTUALIZAR CLIENTE
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.updateCurrentClient(id.getText(), name.getText(), age.getText(), email.getText());
	            done(av);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 500);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(ide, 0, 0);
		grid.add(id, 1, 0);
		grid.add(nam, 0, 1);
		grid.add(name, 1, 1);
		grid.add(eda, 0, 2);
		grid.add(age, 1, 2);
		grid.add(ema, 0, 3);
		grid.add(email, 1, 3);
		grid.add(aceptar, 0, 6);
		grid.add(cancelar, 1, 6);
		

        newWindow.setTitle("Actualizar Cliente");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void registrarClienteLeal(ActionEvent av) {
		Label nom = new Label("Nombre");
		
		Label ide = new Label("ID");
		
		Label eda = new Label("Edad");
		
		Label ema = new Label("Email");
		
		Label dis = new Label("Descuento");
		
		Label poi = new Label("Puntos");
		
		Label car = new Label("Nombre del banco");
		
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField id = new TextField();
		id.setMaxWidth(200);
				
		TextField age = new TextField();
		age.setMaxWidth(200);
				
		TextField email = new TextField();
		email.setMaxWidth(200);
		
		TextField discount = new TextField();
		discount.setMaxWidth(200);
		
		TextField points = new TextField();
		points.setMaxWidth(200);
		
		TextField card = new TextField();
		card.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR CLIENTE
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.addLoyalClient(id.getText(), name.getText(), age.getText(), email.getText(), points.getText(), discount.getText(), card.getText());
	            done(av);
	        } catch (repeatedCustomerException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE EL CLIENTE ESTA REPETIDO
	        	Label errorLabel = new Label("El Cliente ya se encuentra registrado");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 500);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(nom, 0, 0);
		grid.add(name, 1, 0);
		grid.add(ide, 0, 1);
		grid.add(id, 1, 1);
		grid.add(eda, 0, 2);
		grid.add(age, 1, 2);
		grid.add(ema, 0, 3);
		grid.add(email, 1, 3);
		grid.add(dis, 0, 4);
		grid.add(discount, 1, 4);
		grid.add(poi, 0, 5);
		grid.add(points, 1, 5);
		grid.add(car, 0, 6);
		grid.add(card, 1, 6);
		grid.add(aceptar, 0, 7);
		grid.add(cancelar, 1, 7);
		

        newWindow.setTitle("Registrar Cliente");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void actualizarClienteLeal(ActionEvent av) {
		Label ide = new Label("ID");
		
		Label nam = new Label("Nombre");
		
		Label eda = new Label("Edad");
		
		Label ema = new Label("E-mail");
		
		Label dis = new Label("Descuento");
		
		Label poi = new Label("Puntos");
		
		Label car = new Label("Nombre del banco");
		
	
		TextField id = new TextField();
		id.setMaxWidth(200);
		
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField age = new TextField();
		age.setMaxWidth(200);
				
		TextField email = new TextField();
		email.setMaxWidth(200);
		
		TextField discount = new TextField();
		discount.setMaxWidth(200);
		
		TextField points = new TextField();
		points.setMaxWidth(200);
		
		TextField card = new TextField();
		card.setMaxWidth(200);
		
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE ACTUALIZAR CLIENTE LEAL
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.updateLoyalClient(id.getText(), name.getText(), age.getText(), email.getText(), points.getText(), discount.getText(), card.getText());
	            done(av);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 500);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(nam, 0, 0);
		grid.add(name, 1, 0);
		grid.add(ide, 0, 1);
		grid.add(id, 1, 1);
		grid.add(eda, 0, 2);
		grid.add(age, 1, 2);
		grid.add(ema, 0, 3);
		grid.add(email, 1, 3);
		grid.add(dis, 0, 4);
		grid.add(discount, 1, 4);
		grid.add(poi, 0, 5);
		grid.add(points, 1, 5);
		grid.add(car, 1, 6);
		grid.add(card, 1, 6);
		grid.add(aceptar, 0, 7);
		grid.add(cancelar, 1, 7);
		

        newWindow.setTitle("Actualizar Cliente");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void registrarInmoviliariaPrivada(ActionEvent av) {
		Label qua = new Label("Cantidad");
		
		Label buy = new Label("Anio de Compra");
		
		Label nam = new Label("Nombre");
		
		Label ide = new Label("ID");
		
		TextField quantity = new TextField();
		quantity.setMaxWidth(200);
				
		TextField buyYear = new TextField();
		buyYear.setMaxWidth(200);
				
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField id = new TextField();
		id.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR INMOVILIARIA PRIVADA
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.createNewPrivateState(quantity.getText(), buyYear.getText(), name.getText(), id.getText());
	            done(av);
	        } catch (unavaiableIdException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE LA INMOVILIARIA ESTA REPETIDA
	        	Label errorLabel = new Label("La inmoviliaria ya se encuentra registrada");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 300);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(qua, 0, 0);
		grid.add(quantity, 1, 0);
		grid.add(buy, 0, 1);
		grid.add(buyYear, 1, 1);
		grid.add(nam, 0, 2);
		grid.add(name, 1, 2);
		grid.add(ide, 0, 3);
		grid.add(id, 1, 3);
		grid.add(aceptar, 0, 4);
		grid.add(cancelar, 1, 4);
		

        newWindow.setTitle("Registrar Inmoviliaria");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
//	public void actualizarInmoviliariaPrivada(ActionEvent av) {
//		Label qua = new Label("Cantidad");
//		
//		Label buy = new Label("Anio de Compra");
//		
//		Label nam = new Label("Nombre");
//		
//		Label ide = new Label("ID");
//		
//		TextField quantity = new TextField();
//		quantity.setMaxWidth(200);
//				
//		TextField buyYear = new TextField();
//		buyYear.setMaxWidth(200);
//				
//		TextField name = new TextField();
//		name.setMaxWidth(200);
//				
//		TextField id = new TextField();
//		id.setMaxWidth(200);
//		
//		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR INMOVILIARIA PRIVADA
//		Button aceptar = new Button();
//		aceptar.setText("Aceptar");
//		aceptar.setOnAction(event -> {
//	        try {
//	            sma.updatePrivateState(quantity.getText(), buyYear.getText(), name.getText(), id.getText());
//	            done(av);
//	        } catch (Exception e) {
//	        	e.printStackTrace();
//	        }
//	    });
//
//		
//		GridPane grid = new GridPane();
//		grid.setHgap(20);
//		grid.setVgap(20);
//		grid.setPadding(new Insets(120));
//		
//		StackPane secondaryLayout = new StackPane();
//		Scene secondScene = new Scene(secondaryLayout, 600, 300);
//		Stage newWindow = new Stage();
//		
//		
//		Button cancelar = new Button();
//		cancelar.setText("Cancelar");
//		cancelar.setOnAction(event -> {
//			newWindow.close();
//		});
//		
//		secondaryLayout.getChildren().add(grid);
//		grid.add(qua, 0, 0);
//		grid.add(quantity, 1, 0);
//		grid.add(buy, 0, 1);
//		grid.add(buyYear, 1, 1);
//		grid.add(nam, 0, 2);
//		grid.add(name, 1, 2);
//		grid.add(ide, 0, 3);
//		grid.add(id, 1, 3);
//		grid.add(aceptar, 0, 4);
//		grid.add(cancelar, 1, 4);
//		
//
//        newWindow.setTitle("Registrar Inmoviliaria");
//        newWindow.setScene(secondScene);
//
//        newWindow.show();
//	}
	
	
	public void registrarInmoviliariaPublica(ActionEvent av) {
		Label qua = new Label("Cantidad");
		
		Label buy = new Label("Anio de Compra");
		
		Label nam = new Label("Nombre");
		
		Label ide = new Label("ID");
		
		Label mai = new Label("Mantenimiento");
		
		TextField quantity = new TextField();
		quantity.setMaxWidth(200);
				
		TextField buyYear = new TextField();
		buyYear.setMaxWidth(200);
				
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField id = new TextField();
		id.setMaxWidth(200);
		
		TextField maintenance = new TextField();
		maintenance.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR INMOVILIARIA PUBLICA
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.createNewPublicState(quantity.getText(), buyYear.getText(), name.getText(), id.getText(), maintenance.getText());
	            done(av);
	        } catch (unavaiableIdException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE LA INMOVILIARIA ESTA REPETIDA
	        	Label errorLabel = new Label("La inmoviliaria ya se encuentra registrada");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 400);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(qua, 0, 0);
		grid.add(quantity, 1, 0);
		grid.add(buy, 0, 1);
		grid.add(buyYear, 1, 1);
		grid.add(nam, 0, 2);
		grid.add(name, 1, 2);
		grid.add(ide, 0, 3);
		grid.add(id, 1, 3);
		grid.add(mai, 0, 4);
		grid.add(maintenance, 1, 4);
		grid.add(aceptar, 0, 5);
		grid.add(cancelar, 1, 5);
		

        newWindow.setTitle("Registrar Inmoviliaria");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void registrarProductoUnidad(ActionEvent av) {
		Label ide = new Label("ID");
		
		Label nam = new Label("Nombre");
		
		Label bes = new Label("Fecha de vencimiento");
		
		Label pri = new Label("Precio");
		
		Label pro = new Label("Tipo de producto");
		
		Label qua = new Label("Cantidad");
		
		TextField id = new TextField();
		id.setMaxWidth(200);
		
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField bestBefore = new TextField();
		bestBefore.setMaxWidth(200);
				
		TextField price = new TextField();
		price.setMaxWidth(200);
		
		TextField productType = new TextField();
		productType.setMaxWidth(200);
		
		TextField quantity = new TextField();
		quantity.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR PRODUCTO DE UNIDAD
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.addUnityProduct(id.getText(), name.getText(), bestBefore.getText(), price.getText(), productType.getText(), quantity.getText());
	            done(av);
	        } catch (unavaiableIdException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE EL PRODUCTO ESTA REPETIDO
	        	Label errorLabel = new Label("El producto ya se encuentra registrado");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 500);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(ide, 0, 0);
		grid.add(id, 1, 0);
		grid.add(nam, 0, 1);
		grid.add(name, 1, 1);
		grid.add(bes, 0, 2);
		grid.add(bestBefore, 1, 2);
		grid.add(pri, 0, 3);
		grid.add(price, 1, 3);
		grid.add(pro, 0, 4);
		grid.add(productType, 1, 4);
		grid.add(qua, 0, 5);
		grid.add(quantity, 1, 5);
		grid.add(aceptar, 0, 6);
		grid.add(cancelar, 1, 6);
		

        newWindow.setTitle("Registrar Producto");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void actualizarProductoDeUnidad(ActionEvent av) {
		Label ide = new Label("ID");
		
		Label nam = new Label("Nombre");
		
		Label bes = new Label("Fecha de vencimiento");
		
		Label pri = new Label("Precio");
		
		Label pro = new Label("Tipo de producto");
		
		Label qua = new Label("Cantidad");
		
		TextField id = new TextField();
		id.setMaxWidth(200);
		
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField bestBefore = new TextField();
		bestBefore.setMaxWidth(200);
				
		TextField price = new TextField();
		price.setMaxWidth(200);
		
		TextField productType = new TextField();
		productType.setMaxWidth(200);
		
		TextField quantity = new TextField();
		quantity.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE ACTUALIZAR PRODUCTO DE UNIDAD
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.updateUnityProduct(id.getText(), name.getText(), bestBefore.getText(), price.getText(), productType.getText(), quantity.getText());
	            done(av);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 500);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(ide, 0, 0);
		grid.add(id, 1, 0);
		grid.add(nam, 0, 1);
		grid.add(name, 1, 1);
		grid.add(bes, 0, 2);
		grid.add(bestBefore, 1, 2);
		grid.add(pri, 0, 3);
		grid.add(price, 1, 3);
		grid.add(pro, 0, 4);
		grid.add(productType, 1, 4);
		grid.add(qua, 0, 5);
		grid.add(quantity, 1, 5);
		grid.add(aceptar, 0, 6);
		grid.add(cancelar, 1, 6);
		

        newWindow.setTitle("Actualizar Producto");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void registrarProductoPeso(ActionEvent av) {
		Label ide = new Label("ID");
		
		Label nam = new Label("Nombre");
		
		Label bes = new Label("Fecha de vencimiento");
		
		Label pri = new Label("Precio");
		
		Label pro = new Label("Tipo de producto");
		
		Label wei = new Label("Cantidad");
		
		TextField id = new TextField();
		id.setMaxWidth(200);
		
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField bestBefore = new TextField();
		bestBefore.setMaxWidth(200);
				
		TextField price = new TextField();
		price.setMaxWidth(200);
		
		TextField productType = new TextField();
		productType.setMaxWidth(200);
		
		TextField weight = new TextField();
		weight.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR PRODUCTO DE PESO
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.addWeightProduct(id.getText(), name.getText(), bestBefore.getText(), price.getText(), productType.getText(), weight.getText());
	            done(av);
	        } catch (unavaiableIdException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE EL PRODUCTO ESTA REPETIDO
	        	Label errorLabel = new Label("El producto ya se encuentra registrado");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 500);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(ide, 0, 0);
		grid.add(id, 1, 0);
		grid.add(nam, 0, 1);
		grid.add(name, 1, 1);
		grid.add(bes, 0, 2);
		grid.add(bestBefore, 1, 2);
		grid.add(pri, 0, 3);
		grid.add(price, 1, 3);
		grid.add(pro, 0, 4);
		grid.add(productType, 1, 4);
		grid.add(wei, 0, 5);
		grid.add(weight, 1, 5);
		grid.add(aceptar, 0, 6);
		grid.add(cancelar, 1, 6);
		

        newWindow.setTitle("Registrar Producto");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void actualizarProductoDePeso(ActionEvent av) {
		Label ide = new Label("ID");
		
		Label nam = new Label("Nombre");
		
		Label bes = new Label("Fecha de vencimiento");
		
		Label pri = new Label("Precio");
		
		Label pro = new Label("Tipo de producto");
		
		Label wei = new Label("Cantidad en kg.");
		
		TextField id = new TextField();
		id.setMaxWidth(200);
		
		TextField name = new TextField();
		name.setMaxWidth(200);
				
		TextField bestBefore = new TextField();
		bestBefore.setMaxWidth(200);
				
		TextField price = new TextField();
		price.setMaxWidth(200);
		
		TextField productType = new TextField();
		productType.setMaxWidth(200);
		
		TextField weight = new TextField();
		weight.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR PRODUCTO DE PESO
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.updateWeightProduct(id.getText(), name.getText(), bestBefore.getText(), price.getText(), productType.getText(), weight.getText());
	            done(av);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 500);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(ide, 0, 0);
		grid.add(id, 1, 0);
		grid.add(nam, 0, 1);
		grid.add(name, 1, 1);
		grid.add(bes, 0, 2);
		grid.add(bestBefore, 1, 2);
		grid.add(pri, 0, 3);
		grid.add(price, 1, 3);
		grid.add(pro, 0, 4);
		grid.add(productType, 1, 4);
		grid.add(wei, 0, 5);
		grid.add(weight, 1, 5);
		grid.add(aceptar, 0, 6);
		grid.add(cancelar, 1, 6);
		

        newWindow.setTitle("Actualizar Producto");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void registrarGerente(ActionEvent av) {
		Label ide = new Label("ID");
		
		Label nam = new Label("Nombre");
		
		Label eps = new Label("Nombre de la EPS");
		
		Label sal = new Label("Salario");
		
		Label exp = new Label("Experiencia");
		
		Label con = new Label("Contrato");
		
		TextField name = new TextField();
		name.setMaxWidth(200);
		
		TextField id = new TextField();
		id.setMaxWidth(200);
				
		TextField eeps = new TextField();
		eeps.setMaxWidth(200);
				
		TextField salary = new TextField();
		salary.setMaxWidth(200);
		
		TextField experience = new TextField();
		experience.setMaxWidth(200);
		
		TextField contract = new TextField();
		contract.setMaxWidth(200);
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR GERENTE
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.createNewManager(name.getText(), id.getText(), eeps.getText(), salary.getText(), experience.getText(), contract.getText());
	            done(av);
	        } catch (unavaiableIdException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE EL GERENTE ESTA REPETIDO
	        	Label errorLabel = new Label("El gerente ya se encuentra registrado");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 500);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(nam, 0, 0);
		grid.add(name, 1, 0);
		grid.add(ide, 0, 1);
		grid.add(id, 1, 1);
		grid.add(eps, 0, 2);
		grid.add(eeps, 1, 2);
		grid.add(sal, 0, 3);
		grid.add(salary, 1, 3);
		grid.add(exp, 0, 4);
		grid.add(experience, 1, 4);
		grid.add(con, 0, 5);
		grid.add(contract, 1, 5);
		grid.add(aceptar, 0, 6);
		grid.add(cancelar, 1, 6);
		

        newWindow.setTitle("Registrar Gerente");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void registrarAdministrador(ActionEvent av) {
		Label ide = new Label("ID");
		
		Label nam = new Label("Nombre");
		
		Label eps = new Label("Nombre de la EPS");
		
		Label sal = new Label("Salario");
		
		Label exp = new Label("Experiencia");
		
		
		TextField name = new TextField();
		name.setMaxWidth(200);
		
		TextField id = new TextField();
		id.setMaxWidth(200);
				
		TextField eeps = new TextField();
		eeps.setMaxWidth(200);
				
		TextField salary = new TextField();
		salary.setMaxWidth(200);
		
		TextField experience = new TextField();
		experience.setMaxWidth(200);
		
		
		
		//ESTE BOTON SE ENCARGAR DE LLAMAR EL METODO DE REGISTRAR ADMINISTRADOR
		Button aceptar = new Button();
		aceptar.setText("Aceptar");
		aceptar.setOnAction(event -> {
	        try {
	            sma.createNewAdministrator(name.getText(), id.getText(), eeps.getText(), salary.getText(), experience.getText());
	            done(av);
	        } catch (unavaiableIdException e) {
	        	//SE CREA UNA VENTANA QUE INDICA QUE EL ADMINISTRADOR ESTA REPETIDO
	        	Label errorLabel = new Label("El administrador ya se encuentra registrado");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	    });

		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 400);
		Stage newWindow = new Stage();
		
		
		Button cancelar = new Button();
		cancelar.setText("Cancelar");
		cancelar.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(nam, 0, 0);
		grid.add(name, 1, 0);
		grid.add(ide, 0, 1);
		grid.add(id, 1, 1);
		grid.add(eps, 0, 2);
		grid.add(eeps, 1, 2);
		grid.add(sal, 0, 3);
		grid.add(salary, 1, 3);
		grid.add(exp, 0, 4);
		grid.add(experience, 1, 4);

		grid.add(aceptar, 0, 5);
		grid.add(cancelar, 1, 5);
		

        newWindow.setTitle("Registrar Administrador");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	
	public void eliminarInmoviliaria(ActionEvent av) {
		GridPane gpe = new GridPane();
		gpe.setAlignment(Pos.CENTER);
		Label l = new Label ("Inserte la ID de la inmobiliaria que desea borrar");
		Button b = new Button();
		b.setText("Eliminar");
		TextField tf = new TextField();
		b.setOnAction(eliminarInmoviliaria -> {
			
			String s = sma.deleteRealState(tf.getText());
			Label deleteLabel = new Label(s);
        	GridPane deleteGrid = new GridPane();
        	deleteGrid.setAlignment(Pos.CENTER);
        	Button deleteButton = new Button();
        	deleteButton.setText("OK");
        	
        	StackPane delete = new StackPane();
        		        	
        	Scene deleteScene = new Scene(delete, 300, 100);
            Stage deleteWindow = new Stage();
            
            deleteButton.setOnAction(errorEvent -> {
            	deleteWindow.close();
            });
            
            delete.getChildren().add(deleteGrid);
            deleteGrid.add(deleteLabel, 0, 0);
            deleteGrid.add(deleteButton, 0, 1);
            
            
            deleteWindow.setTitle(" ");
            deleteWindow.setScene(deleteScene);

            deleteWindow.show();
		});
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 150);
		Stage newWindow = new Stage();
		
		secondaryLayout.getChildren().add(gpe);
		gpe.add(l, 0, 0);
		gpe.add(tf, 0, 1);
		gpe.add(b, 0, 2);
		
		
		newWindow.setTitle("Eliminar Inmoviliaria");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void eliminarTrabajador(ActionEvent av) {
		GridPane gpe = new GridPane();
		gpe.setAlignment(Pos.CENTER);
		Label l = new Label ("Inserte la ID del trabajador que desea borrar");
		Button b = new Button();
		b.setText("Eliminar");
		TextField tf = new TextField();
		b.setOnAction(eliminarTrabajador -> {
			
			String s = sma.deleteWorker(tf.getText());
			Label deleteLabel = new Label(s);
        	GridPane deleteGrid = new GridPane();
        	deleteGrid.setAlignment(Pos.CENTER);
        	Button deleteButton = new Button();
        	deleteButton.setText("OK");
        	
        	StackPane delete = new StackPane();
        	
        	Scene deleteScene = new Scene(delete, 300, 100);
            Stage deleteWindow = new Stage();
            
            deleteButton.setOnAction(errorEvent -> {
            	deleteWindow.close();
            });
            
            delete.getChildren().add(deleteGrid);
            deleteGrid.add(deleteLabel, 0, 0);
            deleteGrid.add(deleteButton, 0, 1);
            
            
            deleteWindow.setTitle(" ");
            deleteWindow.setScene(deleteScene);

            deleteWindow.show();
		});
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 150);
		Stage newWindow = new Stage();
		
		secondaryLayout.getChildren().add(gpe);
		gpe.add(l, 0, 0);
		gpe.add(tf, 0, 1);
		gpe.add(b, 0, 2);
		
		
		newWindow.setTitle("Eliminar Trabajador");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	public void eliminarProductoUnidad(ActionEvent av) {
		GridPane gpe = new GridPane();
		gpe.setAlignment(Pos.CENTER);
		Label l = new Label ("Inserte la ID del producto que desea borrar");
		Button b = new Button();
		b.setText("Eliminar");
		TextField tf = new TextField();
		b.setOnAction(eliminarTrabajador -> {
			try {
				sma.deleteUnityProduct(tf.getText());
				Label deleteLabel = new Label("Se ha eliminado exitosamente");
	        	GridPane deleteGrid = new GridPane();
	        	deleteGrid.setAlignment(Pos.CENTER);
	        	Button deleteButton = new Button();
	        	deleteButton.setText("OK");
	        	
	        	StackPane delete = new StackPane();
	        	
	        	Scene deleteScene = new Scene(delete, 300, 100);
	            Stage deleteWindow = new Stage();
	            
	            deleteButton.setOnAction(errorEvent -> {
	            	deleteWindow.close();
	            });
	            
	            delete.getChildren().add(deleteGrid);
	            deleteGrid.add(deleteLabel, 0, 0);
	            deleteGrid.add(deleteButton, 0, 1);
	            
	            
	            deleteWindow.setTitle(" ");
	            deleteWindow.setScene(deleteScene);
	
	            deleteWindow.show();
			}catch(noMatchesException e) {
				Label errorLabel = new Label("El producto ingresado no existe");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
			}catch(Exception e) {
				
			}
		});
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 150);
		Stage newWindow = new Stage();
		
		secondaryLayout.getChildren().add(gpe);
		gpe.add(l, 0, 0);
		gpe.add(tf, 0, 1);
		gpe.add(b, 0, 2);
		
		
		newWindow.setTitle("Eliminar Producto");
        newWindow.setScene(secondScene);

        newWindow.show();
		
	}
	
	
	public void eliminarProductoPeso(ActionEvent av) {
		GridPane gpe = new GridPane();
		gpe.setAlignment(Pos.CENTER);
		Label l = new Label ("Inserte la ID del producto que desea borrar");
		Button b = new Button();
		b.setText("Eliminar");
		TextField tf = new TextField();
		b.setOnAction(eliminarTrabajador -> {
			try {
				sma.deleteWeightProduct(tf.getText());
				Label deleteLabel = new Label("Se ha eliminado exitosamente");
	        	GridPane deleteGrid = new GridPane();
	        	deleteGrid.setAlignment(Pos.CENTER);
	        	Button deleteButton = new Button();
	        	deleteButton.setText("OK");
	        	
	        	StackPane delete = new StackPane();
	        	
	        	Scene deleteScene = new Scene(delete, 300, 100);
	            Stage deleteWindow = new Stage();
	            
	            deleteButton.setOnAction(errorEvent -> {
	            	deleteWindow.close();
	            });
	            
	            delete.getChildren().add(deleteGrid);
	            deleteGrid.add(deleteLabel, 0, 0);
	            deleteGrid.add(deleteButton, 0, 1);
	            
	            
	            deleteWindow.setTitle(" ");
	            deleteWindow.setScene(deleteScene);
	
	            deleteWindow.show();
			}catch(noMatchesException e) {
				Label errorLabel = new Label("El producto ingresado no existe");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
			}catch(Exception e) {
				
			}
		});
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 150);
		Stage newWindow = new Stage();
		
		secondaryLayout.getChildren().add(gpe);
		gpe.add(l, 0, 0);
		gpe.add(tf, 0, 1);
		gpe.add(b, 0, 2);
		
		
		newWindow.setTitle("Eliminar Producto");
        newWindow.setScene(secondScene);

        newWindow.show();
		
	}
	
	
	public void buscarTrabajador(ActionEvent av) {
		Label l = new Label("Inserte la ID del trabajador");
		
		TextField tf = new TextField();
		tf.setMaxWidth(200);
		
		Button b = new Button();
		b.setText("Buscar");
		
		Label t = new Label();
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		
		b.setOnAction(buscarTrabajador -> {
			try {
			Worker w = sma.searchWorkerBinary(tf.getText());
			t.setText(w.toString());
			}catch(NullPointerException e) {
				Label errorLabel = new Label("El trabajador ingresado no existe");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
			}
		});
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 300, 150);
		Stage newWindow = new Stage();
		
		secondaryLayout.getChildren().add(gp);
		gp.add(l, 0, 0);
		gp.add(tf, 0, 1);
		gp.add(b, 0, 2);
		gp.add(t, 0, 3);
		
		newWindow.setTitle("Buscar Trabajador");
        newWindow.setScene(secondScene);

        newWindow.show();
		
	}
	
	
	public void buscarInmobiliaria(ActionEvent av) {
		Label l = new Label("Inserte la ID de la inmobiliaria");
		
		TextField tf = new TextField();
		tf.setMaxWidth(200);
		
		Button b = new Button();
		b.setText("Buscar");
		
		Label t = new Label();
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		
		b.setOnAction(buscarTrabajador -> {
			try {
			Worker w = sma.searchWorkerBinary(tf.getText());
			t.setText(w.toString());
			}catch(NullPointerException e) {
				Label errorLabel = new Label("El trabajador ingresado no existe");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
			}
		});
		
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 300, 150);
		Stage newWindow = new Stage();
		
		secondaryLayout.getChildren().add(gp);
		gp.add(l, 0, 0);
		gp.add(tf, 0, 1);
		gp.add(b, 0, 2);
		gp.add(t, 0, 3);
		
		newWindow.setTitle("Buscar Trabajador");
        newWindow.setScene(secondScene);

        newWindow.show();
	}
	
	
	public void imprimirFactura() {
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 600, 200);
		Stage newWindow = new Stage();
		
		Label l = new Label("Numero de la factura a imprimir");
		TextField tf = new TextField();
		tf.setMaxWidth(200);
		Button b = new Button();
		b.setText("Imprimir");
		GridPane g = new GridPane();
		
		b.setOnAction(imprimir -> {
			try {
				String s = sma.printInvoice(tf.getText());
				
				StackPane anotherLayout = new StackPane();
				Scene anotherScene = new Scene(anotherLayout, 600, 200);
				Stage anotherWindow = new Stage();
				
				GridPane g0 = new GridPane();
				g0.setAlignment(Pos.CENTER);
				Label factura = new Label(s);
				anotherLayout.getChildren().add(g0);
				g0.add(factura, 0, 0);
				
				
				anotherWindow.setTitle("Factura");
				anotherWindow.setScene(anotherScene);
				anotherWindow.show();
			}catch (noMatchesException e) {
				Label errorLabel = new Label("La factura ingresada no existe");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
			}catch (Exception e) {
				Label errorLabel = new Label("Error al buscar");
	        	GridPane errorGrid = new GridPane();
	        	Button errorButton = new Button();
	        	errorButton.setText("OK");
	        	errorGrid.setAlignment(Pos.CENTER);
	        	
	        	StackPane error = new StackPane();
	        		        	
	        	Scene errorScene = new Scene(error, 300, 100);
	            Stage errorWindow = new Stage();
	            
	            errorButton.setOnAction(errorEvent -> {
	            	errorWindow.close();
	            });
	            
	            error.getChildren().add(errorGrid);
	        	errorGrid.add(errorLabel, 0, 0);
	        	errorGrid.add(errorButton, 0, 1);
	            
	            
	        	errorWindow.setTitle("Error.");
	        	errorWindow.setScene(errorScene);

	        	errorWindow.show();
			}
		});
		
		secondaryLayout.getChildren().add(g);
		g.add(l, 0, 0);
		g.add(tf, 0, 1);
		g.add(b, 0, 2);
		
		newWindow.setTitle("Factura");
        newWindow.setScene(secondScene);
        newWindow.show();
	}
	
	
	public void ordenarTrabajadorId(ActionEvent av) {
		try {
			sma.organizeWithiDInsercion();
			done(av);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void ordenarTrabajadorNombre(ActionEvent av) {
		try {
			sma.organizeWithName();
			done(av);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void ordenarInmobiliariaId(ActionEvent av) {
		try {
			sma.organizeWRealStatesBurbuja();
			done(av);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void ordenarInmobiliariaCantidad(ActionEvent av) {
		try {
			sma.organizeWRealStatesQuantity();
			done(av);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void ordenarProductosId() {
		try {
			sma.getCashRegister().getInvoiceRoot().ordenarSeleccionObjetosId();
		}catch(NullPointerException e) {
			Label errorLabel = new Label("Por favor cree primero una factura");
        	GridPane errorGrid = new GridPane();
        	Button errorButton = new Button();
        	errorButton.setText("OK");
        	errorGrid.setAlignment(Pos.CENTER);
        	
        	StackPane error = new StackPane();
        		        	
        	Scene errorScene = new Scene(error, 300, 100);
            Stage errorWindow = new Stage();
            
            errorButton.setOnAction(errorEvent -> {
            	errorWindow.close();
            });
            
            error.getChildren().add(errorGrid);
        	errorGrid.add(errorLabel, 0, 0);
        	errorGrid.add(errorButton, 0, 1);
            
            
        	errorWindow.setTitle("Error.");
        	errorWindow.setScene(errorScene);

        	errorWindow.show();
		}
	}
	
	
	public void ordenarProductosNombre() {
		try {
			sma.getCashRegister().getInvoiceRoot().ordenarSeleccionObjetosName();
		}catch(NullPointerException e) {
			Label errorLabel = new Label("Por favor cree primero una factura");
        	GridPane errorGrid = new GridPane();
        	Button errorButton = new Button();
        	errorButton.setText("OK");
        	errorGrid.setAlignment(Pos.CENTER);
        	
        	StackPane error = new StackPane();
        		        	
        	Scene errorScene = new Scene(error, 300, 100);
            Stage errorWindow = new Stage();
            
            errorButton.setOnAction(errorEvent -> {
            	errorWindow.close();
            });
            
            error.getChildren().add(errorGrid);
        	errorGrid.add(errorLabel, 0, 0);
        	errorGrid.add(errorButton, 0, 1);
            
            
        	errorWindow.setTitle("Error.");
        	errorWindow.setScene(errorScene);

        	errorWindow.show();
		}
	}
	
	
	public void salir(ActionEvent av) {
		SoundThread st = new SoundThread(this);
		st.start();
		
		StackPane anotherLayout = new StackPane();
		Scene anotherScene = new Scene(anotherLayout, 400, 200);
		Stage anotherWindow = new Stage();
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		
		Canvas canvas = new Canvas(100, 100);
		gp.add(canvas, 1, 0);
		gc = canvas.getGraphicsContext2D();
		Image img = new Image(new File("marketRecords//img.png").toURI().toString());
		gc.fillOval(0, 0, 50, 50);
		gc.drawImage(img, 0, 0, 100, 100);
		
		
		Button si = new Button();
		si.setText("SI");
		
		Button no = new Button();
		no.setText("NO");
		
		si.setOnAction(cerrar -> {
			sma.saveEverything();
			System.exit(0);
		});
		
		no.setOnAction(nocerrar -> {
			anotherWindow.close();
		});
		
		Label l = new Label("Desea Salir?");
		
		anotherLayout.getChildren().add(gp);
		gp.add(l, 0, 0);
		gp.add(si, 0, 1);
		gp.add(no, 1, 1);
		
		anotherWindow.setScene(anotherScene);
		anotherWindow.show();
		
		
	}

}
