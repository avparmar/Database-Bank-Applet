import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;


import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.PreparedStatement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Label;
import javax.swing.JComboBox;



public class GUI {

	private static Connection conn;
	private JFrame frmBank;
	private static JTextField txtId;
	private static JTextField txtPassword;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	protected static int id;
	private JTable table;
	protected static boolean thisIsEmployee;
	protected static boolean thisIsCustomer;
	private JTable table_1;
	private JTextField textField;
	private JTable table_2;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTextField textField_1;
	private JTextField textField_8;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getConnection();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					GUI window = new GUI();
					window.frmBank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBank = new JFrame();
		frmBank.setResizable(false);
		frmBank.setTitle("Bank");
		frmBank.setBounds(100, 100, 852, 496);
		frmBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBank.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel login = new JPanel();


		login.setLayout(null);
		frmBank.getContentPane().add(login, "name_147009347400666");

		txtId = new JTextField("");
		/*txtId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtId.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				txtId.setText("id");
			}
		});*/
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setColumns(10);
		txtId.setBounds(198, 143, 160, 31);

		login.add(txtId);

		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setColumns(10);
		txtPassword.setBounds(198, 185, 160, 31);
		login.add(txtPassword);

		JButton btnLogin = new JButton("login");


		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(236, 227, 89, 31);
		login.add(btnLogin);

		JLabel label = new JLabel("id");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(169, 143, 10, 27);
		login.add(label);

		JLabel label_1 = new JLabel("password");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(121, 187, 58, 23);
		login.add(label_1);

		JLabel label_2 = new JLabel("Bank");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 72));
		label_2.setBounds(10, 11, 160, 125);
		login.add(label_2);

		JLabel lblConnectToThe = new JLabel("Connect to the system as an employee or as a customer.");
		lblConnectToThe.setBounds(29, 442, 329, 14);
		login.add(lblConnectToThe);
		JPanel customerMenu = new JPanel();
		frmBank.getContentPane().add(customerMenu, "name_147022567452058");
		customerMenu.setLayout(null);



		JButton btnBalance = new JButton("balance");






		JButton btnStatement = new JButton("statement");


		JPanel withdraw = new JPanel();
		frmBank.getContentPane().add(withdraw, "name_147028160342425");
		withdraw.setLayout(null);


		JButton btnNewButton = new JButton("withdraw");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerMenu.setVisible(false);
				withdraw.setVisible(true);

			}
		});
		btnNewButton.setBounds(80, 114, 164, 65);
		customerMenu.add(btnNewButton);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setBounds(213, 142, 132, 37);
		withdraw.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmount.setBounds(144, 146, 59, 24);
		withdraw.add(lblAmount);



		JPanel deposit = new JPanel();
		frmBank.getContentPane().add(deposit, "name_147031812937978");
		deposit.setLayout(null);

		JButton btnDeposit = new JButton("deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerMenu.setVisible(false);
				deposit.setVisible(true);
			}
		});
		btnDeposit.setBounds(301, 114, 164, 65);
		customerMenu.add(btnDeposit);
		JLabel lblNewLabel = new JLabel("Amount");
		lblNewLabel.setBounds(164, 141, 46, 14);
		deposit.add(lblNewLabel);

		textField_3 = new JTextField();
		textField_3.setBounds(232, 138, 86, 20);
		deposit.add(textField_3);
		textField_3.setColumns(10);



		JPanel transfer = new JPanel();
		frmBank.getContentPane().add(transfer, "name_147033653821202");
		transfer.setLayout(null);
		JButton btnTransfer = new JButton("transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerMenu.setVisible(false);
				transfer.setVisible(true);
			}
		});
		btnTransfer.setBounds(80, 195, 164, 65);
		customerMenu.add(btnTransfer);



		textField_4 = new JTextField();
		textField_4.setBounds(226, 106, 86, 20);
		transfer.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(226, 156, 86, 20);
		transfer.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Receiver ID");
		lblNewLabel_1.setBounds(155, 109, 88, 14);
		transfer.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Amount");
		lblNewLabel_2.setBounds(155, 159, 46, 14);
		transfer.add(lblNewLabel_2);

		JPanel balance = new JPanel();
		frmBank.getContentPane().add(balance, "name_147783286038774");
		balance.setLayout(null);
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					textField_6 .setText(getBalance(id));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				customerMenu.setVisible(false);
				balance.setVisible(true);
			}
		});
		btnBalance.setBounds(301, 195, 164, 65);
		customerMenu.add(btnBalance);

		JLabel lblNewLabel_3 = new JLabel("Your balance is");
		lblNewLabel_3.setBounds(161, 138, 89, 14);
		balance.add(lblNewLabel_3);



		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(262, 135, 86, 20);
		balance.add(textField_6);
		textField_6.setColumns(10);




		JPanel lastScreen = new JPanel();
		frmBank.getContentPane().add(lastScreen, "name_148089499860365");
		lastScreen.setLayout(null);
		JButton btnWithdraw = new JButton("withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//textField_2
				double amount = Double.parseDouble(textField_2.getText());
				if(amount < 0){
					JOptionPane.showMessageDialog(null,"Can't withdraw negative amount");
				}
				else{
					try {
						withdraw(id,amount);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					withdraw.setVisible(false);
					lastScreen.setVisible(true);
				}
			}
		});
		btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnWithdraw.setBounds(195, 215, 89, 23);
		withdraw.add(btnWithdraw);

		JPanel statement = new JPanel();
		frmBank.getContentPane().add(statement, "name_147840364087652");

		btnStatement.addActionListener(new ActionListener() {//set the table in statement panel
			public void actionPerformed(ActionEvent e) {
				//print transactions table to statement panel 
				try {
					String transTableQ = "Select transaction_number, transaction_date, transaction_time, "
							+ "transaction_type, old_balance, amount_transacted, "
							+ "new_balance from transactions where customer_id = "+ id+";";
					java.sql.PreparedStatement st11 = conn.prepareStatement(transTableQ);
					ResultSet rs11 = st11.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs11));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				customerMenu.setVisible(false);
				statement.setVisible(true);
			}
		});
		btnStatement.setBounds(80, 277, 164, 65);
		customerMenu.add(btnStatement);

		JLabel lblConnectedToCustomers = new JLabel("Connected to customer's system");
		lblConnectedToCustomers.setBounds(10, 442, 192, 14);
		customerMenu.add(lblConnectedToCustomers);

		JButton btnDone_1 = new JButton("done");
		btnDone_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDone_1.setBounds(341, 433, 89, 23);
		btnDone_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				statement.setVisible(false);
				lastScreen.setVisible(true);
			}
		});
		statement.setLayout(null);
		statement.add(btnDone_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 764, 422);
		statement.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnDone = new JButton("done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				balance.setVisible(false);
				lastScreen.setVisible(true);
			}
		});
		btnDone.setBounds(259, 190, 89, 23);
		balance.add(btnDone);

		JButton btnNewButton_1 = new JButton("deposit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double amount = Double.parseDouble(textField_3.getText());
				if(amount < 0){
					JOptionPane.showMessageDialog(null,"Can't deposit negative amount");
				}
				else{
					try {
						deposit(id,amount);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					deposit.setVisible(false);
					lastScreen.setVisible(true);
				}
			}
		});
		btnNewButton_1.setBounds(187, 177, 89, 23);
		deposit.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Transfer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double amount = Double.parseDouble(textField_5.getText());
				int receiverID = Integer.parseInt(textField_4.getText());

				if(amount < 0){
					JOptionPane.showMessageDialog(null,"Can't transfer negative amount");
				} else
					try {
						if(!validID(receiverID)){
							JOptionPane.showMessageDialog(null,"can't find id");

						}
						else{
							try {
								transfer(id,receiverID,amount);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							transfer.setVisible(false);
							lastScreen.setVisible(true);
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}


		});
		btnNewButton_2.setBounds(188, 206, 89, 23);
		transfer.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("done");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setBounds(317, 253, 89, 23);
		lastScreen.add(btnNewButton_3);

		JButton btnDoMore = new JButton("Do more");

		btnDoMore.setBounds(130, 253, 89, 23);
		lastScreen.add(btnDoMore);

		JPanel employeeMenu = new JPanel();
		btnDoMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thisIsCustomer){
					lastScreen.setVisible(false);
					customerMenu.setVisible(true);
				}
				if(thisIsEmployee){
					lastScreen.setVisible(false);
					employeeMenu.setVisible(true);
				}
			}
		});
		frmBank.getContentPane().add(employeeMenu, "name_163522212472782");
		employeeMenu.setLayout(null);
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					id = Integer.parseInt(txtId.getText());
					if(login()){

						//changing screen

						if(thisIsCustomer){
							if(!checkFrozen()){
								login.setVisible(false);
								customerMenu.setVisible(true);
							}
							else{
								JOptionPane.showMessageDialog(null, "Account Frozen");
								thisIsCustomer = false;

							}
						}
						if(thisIsEmployee){
							login.setVisible(false);
							employeeMenu.setVisible(true);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Wrong password or id");
					thisIsCustomer = false;
					thisIsEmployee = false;
					//e.printStackTrace();
				}

			}

			private boolean checkFrozen() throws SQLException {
				boolean isFrozen;
				java.sql.PreparedStatement s50 = conn.prepareStatement("select account_status from customers where id = "+ id+";");
				ResultSet rs50 = s50.executeQuery();
				rs50.next();
				String tempStatus = rs50.getString("account_status");
				if(tempStatus != null && tempStatus.equals("frozen")) 
					return true;
				return false;


			}
		});

		JButton btnCreateAccount = new JButton("Create Account");

		btnCreateAccount.setBounds(66, 82, 164, 65);
		employeeMenu.add(btnCreateAccount);

		JButton btnFreezeAccount = new JButton("Freeze Account");

		btnFreezeAccount.setBounds(66, 163, 164, 65);


		JButton btnCheckAccount = new JButton("Check Account");

		btnCheckAccount.setBounds(66, 245, 164, 65);
		employeeMenu.add(btnCheckAccount);

		JButton btnDeleteAccount = new JButton("Delete Account");

		btnDeleteAccount.setBounds(287, 82, 164, 65);
		employeeMenu.add(btnDeleteAccount);

		JButton btnModifyAccount = new JButton("Modify Account");

		btnModifyAccount.setBounds(287, 163, 164, 65);
		employeeMenu.add(btnModifyAccount);

		JButton btnCheckAllAccount = new JButton("Check All Accounts");

		btnCheckAllAccount.setBounds(287, 245, 164, 65);
		employeeMenu.add(btnCheckAllAccount);

		JLabel lblConnectedToEmployee = new JLabel("Connected to employee's system");
		lblConnectedToEmployee.setBounds(10, 442, 220, 14);
		employeeMenu.add(lblConnectedToEmployee);

		JPanel createAccount = new JPanel();
		createAccount.setForeground(Color.WHITE);
		frmBank.getContentPane().add(createAccount, "name_163951250915726");
		createAccount.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setBounds(171, 63, 86, 20);
		createAccount.add(textField_1);
		textField_1.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(171, 111, 86, 20);
		createAccount.add(textField_8);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(171, 159, 86, 20);
		createAccount.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(171, 213, 86, 20);
		createAccount.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(171, 263, 86, 20);
		createAccount.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(171, 319, 86, 20);
		createAccount.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(556, 63, 86, 20);
		createAccount.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(556, 111, 86, 20);
		createAccount.add(textField_15);

		JLabel lblNewLabel_5 = new JLabel("id");
		lblNewLabel_5.setBounds(84, 66, 22, 14);
		createAccount.add(lblNewLabel_5);

		JLabel lblPin = new JLabel("account number");
		lblPin.setBounds(84, 216, 77, 14);
		createAccount.add(lblPin);

		JLabel lblFirstName = new JLabel("first name");
		lblFirstName.setBounds(84, 116, 54, 14);
		createAccount.add(lblFirstName);

		JLabel lblLastName = new JLabel("last name");
		lblLastName.setBounds(84, 162, 48, 14);
		createAccount.add(lblLastName);

		JLabel label_7 = new JLabel("pin");
		label_7.setBounds(84, 266, 22, 14);
		createAccount.add(label_7);

		JLabel lblBalance = new JLabel("balance");
		lblBalance.setBounds(84, 322, 37, 14);
		createAccount.add(lblBalance);

		JLabel lblCountry = new JLabel("country");
		lblCountry.setBounds(486, 66, 48, 14);
		createAccount.add(lblCountry);

		JLabel lblCity = new JLabel("city");
		lblCity.setBounds(486, 116, 22, 14);
		createAccount.add(lblCity);

		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(556, 162, 86, 20);
		createAccount.add(textField_16);

		JLabel lblZip = new JLabel("street");
		lblZip.setBounds(486, 162, 48, 14);
		createAccount.add(lblZip);

		JLabel lblZip_1 = new JLabel("zip");
		lblZip_1.setBounds(486, 216, 22, 14);
		createAccount.add(lblZip_1);

		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(556, 210, 86, 20);
		createAccount.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setColumns(10);
		textField_18.setBounds(556, 263, 86, 20);
		createAccount.add(textField_18);

		JLabel lblRegion = new JLabel("region");
		lblRegion.setBounds(486, 266, 39, 14);
		createAccount.add(lblRegion);

		JLabel lblSalary = new JLabel("salary");
		lblSalary.setBounds(486, 322, 37, 14);
		createAccount.add(lblSalary);

		textField_19 = new JTextField();
		textField_19.setColumns(10);
		textField_19.setBounds(556, 319, 86, 20);
		createAccount.add(textField_19);

		Label label_5 = new Label("Required fields");
		label_5.setBackground(Color.RED);
		label_5.setBounds(117, 21, 86, 22);
		createAccount.add(label_5);

		JLabel lblExtraFields = new JLabel("Extra fields");
		lblExtraFields.setBackground(Color.WHITE);
		lblExtraFields.setForeground(Color.GREEN);
		lblExtraFields.setBounds(531, 21, 77, 22);
		createAccount.add(lblExtraFields);

		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get Data
				int addID = Integer.parseInt(textField_1.getText());
				String first_name = textField_8.getText();
				System.out.println("first_name" + first_name);
				String last_name = textField_10.getText();
				System.out.println("last_name" + last_name);

				long accountNum = Long.parseLong(textField_11.getText());
				int pin = Integer.parseInt(textField_12.getText());
				double balance = Double.parseDouble(textField_13.getText());

				//get extra
				String country = textField_14.getText();
				String city = textField_15.getText();
				String street = textField_16.getText();
				String zip = textField_17.getText();
				String region = textField_18.getText();
				String salary = textField_19.getText();
				try {
					createUser(addID, first_name, last_name);

					if (!country.equals("")){
						updateUser(addID, "country", country);
					}
					if (!city.equals("")){
						updateUser(addID, "city", city);
					}
					if (!street.equals("")){
						updateUser(addID, "street_address", street);
					}
					if (!region.equals("")){
						updateUser(addID, "region", region);
					}
					if (!zip.equals("")){
						updateUser(addID, "zip", zip);
					}
					if (!salary.equals("")){
						updateUser(addID, "salary", salary);
					}


				} catch (SQLException e1) {
					System.out.println("can't create user");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}




				try {
					createCustomerAccount(addID, pin, accountNum, balance);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("can't create customer");
					e.printStackTrace();
				}


				createAccount.setVisible(false);
				lastScreen.setVisible(true);

			}
		});
		btnAddAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddAccount.setBounds(344, 398, 111, 23);
		createAccount.add(btnAddAccount);
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeMenu.setVisible(false);
				createAccount.setVisible(true);
			}
		});

		JPanel deleteAccount = new JPanel();
		frmBank.getContentPane().add(deleteAccount, "name_164011139966606");
		deleteAccount.setLayout(null);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idToDelete = Integer.parseInt(textField_7.getText());
				try {
					deleteAccount(idToDelete);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				deleteAccount.setVisible(false);
				lastScreen.setVisible(true);

			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(470, 222, 89, 23);
		deleteAccount.add(btnDelete);

		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(329, 222, 131, 23);
		deleteAccount.add(textField_7);

		JLabel label_3 = new JLabel("Enter Account ID");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(215, 222, 131, 23);
		deleteAccount.add(label_3);

		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeMenu.setVisible(false);
				deleteAccount.setVisible(true);
			}
		});

		JPanel freezeAccount = new JPanel();
		frmBank.getContentPane().add(freezeAccount, "name_164038073498471");
		freezeAccount.setLayout(null);

		JLabel label_4 = new JLabel("Enter Account ID");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(221, 245, 131, 23);
		freezeAccount.add(label_4);

		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_9.setColumns(10);
		textField_9.setBounds(335, 245, 131, 23);
		freezeAccount.add(textField_9);

		JButton btnFreeze = new JButton("Freeze");
		btnFreeze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int freezeID = Integer.parseInt(textField_9.getText());
				try {
					freezeAccount(freezeID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				freezeAccount.setVisible(false);
				lastScreen.setVisible(true);

			}
		});
		btnFreeze.setBackground(UIManager.getColor("Button.background"));
		btnFreeze.setForeground(Color.BLACK);
		btnFreeze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFreeze.setBounds(476, 245, 89, 23);
		freezeAccount.add(btnFreeze);

		JButton btnUnfreeze = new JButton("Unfreeze");
		btnUnfreeze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int unFreezeID = Integer.parseInt(textField_9.getText());
				////////adasdsafsaf
				try {
					unFreezeAccount(unFreezeID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				freezeAccount.setVisible(false);
				lastScreen.setVisible(true);
			}
		});
		btnUnfreeze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUnfreeze.setBounds(575, 245, 89, 23);
		freezeAccount.add(btnUnfreeze);

		btnFreezeAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeMenu.setVisible(false);
				freezeAccount.setVisible(true);
			}
		});
		employeeMenu.add(btnFreezeAccount);

		JPanel modifyAccount = new JPanel();
		frmBank.getContentPane().add(modifyAccount, "name_164175890983074");
		modifyAccount.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(272, 101, 132, 20);
		comboBox.addItem("first_name");
		comboBox.addItem("last_name");
		comboBox.addItem("pin");
		comboBox.addItem("city");
		comboBox.addItem("country");
		comboBox.addItem("region");
		comboBox.addItem("street_address");
		comboBox.addItem("zip");
		comboBox.addItem("salary");
		comboBox.addItem("balance");
		comboBox.addItem("account_number");

		modifyAccount.add(comboBox);

		textField_20 = new JTextField();
		textField_20.setBounds(164, 101, 86, 20);
		modifyAccount.add(textField_20);
		textField_20.setColumns(10);

		textField_21 = new JTextField();
		textField_21.setBounds(427, 101, 86, 20);
		modifyAccount.add(textField_21);
		textField_21.setColumns(10);

		JButton btnNewButton_4 = new JButton("update");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				int modifyingID = Integer.parseInt(textField_20.getText());
				String newValue = textField_21.getText();
				String fieldName = (String) comboBox.getSelectedItem();

				if(fieldName.equals("pin") || fieldName.equals("account_number") || fieldName.equals("balance")){
					//customer
					try {
						updateCustomer(modifyingID, fieldName, newValue);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else{
					//user
					try {
						updateUser(modifyingID, fieldName, newValue);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}


				modifyAccount.setVisible(false);
				lastScreen.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(290, 146, 101, 23);
		modifyAccount.add(btnNewButton_4);

		JLabel lblId = new JLabel("id");
		lblId.setBounds(204, 76, 46, 14);
		modifyAccount.add(lblId);

		JLabel lblNewValue = new JLabel("new value");
		lblNewValue.setBounds(440, 76, 73, 14);
		modifyAccount.add(lblNewValue);

		JLabel lblChooseField = new JLabel("choose field");
		lblChooseField.setBounds(300, 76, 73, 14);
		modifyAccount.add(lblChooseField);

		btnModifyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeMenu.setVisible(false);
				modifyAccount.setVisible(true);

			}
		});

		JPanel checkAllAccount = new JPanel();
		frmBank.getContentPane().add(checkAllAccount, "name_164168975833795");
		checkAllAccount.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 846, 424);
		checkAllAccount.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JButton button = new JButton("done");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkAllAccount.setVisible(false);
				lastScreen.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(379, 433, 89, 23);
		checkAllAccount.add(button);

		btnCheckAllAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//print users and customers table to check all accounts panel 
				try {
					String allAccountsQ = "SELECT users.first_name, users.last_name,users.id, users.street_address,"
							+ "users.city, users.zip, users.country, users.region, users.salary, customers.account_number,"
							+ " customers.balance, customers.last_trans, customers.last_amount_trans, customers.account_status "
							+ "FROM bank.users, bank.customers where customers.id = users.id;";

					java.sql.PreparedStatement st12 = conn.prepareStatement(allAccountsQ);
					ResultSet rs12 = st12.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs12));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				employeeMenu.setVisible(false);
				checkAllAccount.setVisible(true);
			}
		});

		JPanel checkAccount = new JPanel();
		frmBank.getContentPane().add(checkAccount, "name_164118805018519");
		checkAccount.setLayout(null);

		JButton button_1 = new JButton("done");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAccount.setVisible(false);
				lastScreen.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBounds(747, 435, 89, 23);
		checkAccount.add(button_1);

		JLabel lblNewLabel_4 = new JLabel("Enter Account ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 435, 131, 23);
		checkAccount.add(lblNewLabel_4);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(120, 435, 131, 23);
		checkAccount.add(textField);
		textField.setColumns(10);

		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int wantedID = Integer.parseInt(textField.getText());
				//print one user and customer table to check accounts panel
				try {
					if(!validID(wantedID)){
						JOptionPane.showMessageDialog(null,"can't find id");
					}
					else{


						try {
							String accountsQ = "SELECT users.first_name, users.last_name,users.id, users.street_address,"
									+ "users.city, users.zip, users.country, users.region, users.salary, customers.account_number,"
									+ " customers.balance, customers.last_trans, customers.last_amount_trans, customers.account_status "
									+ "FROM bank.users, bank.customers where  customers.id = "+wantedID+" and customers.id = users.id;";

							java.sql.PreparedStatement st13 = conn.prepareStatement(accountsQ);
							ResultSet rs13 = st13.executeQuery();
							table_2.setModel(DbUtils.resultSetToTableModel(rs13));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCheck.setBounds(261, 435, 89, 23);
		checkAccount.add(btnCheck);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 846, 424);
		checkAccount.add(scrollPane_2);

		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);

		btnCheckAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				employeeMenu.setVisible(false);
				checkAccount.setVisible(true);
			}
		});

	}
	private static boolean login() throws Exception {

		java.sql.PreparedStatement s66 = conn.prepareStatement("select * from employees where id = "+ txtId.getText()+";");
		s66.execute();
		ResultSet rs9 = s66.getResultSet ();
		java.sql.PreparedStatement s2 = conn.prepareStatement("select * from customers where id = "+txtId.getText() +";");
		s2.execute();
		ResultSet rs10 = s2.getResultSet ();
		boolean idFound = false;

		while(rs9.next()){//employee
			thisIsEmployee = true;
			String password = txtPassword.getText();
			String DBPassword = rs9.getString("login_password");
			idFound = true;
			if(password.equals(DBPassword)){
				return true;
			}else{
				JOptionPane.showMessageDialog(null, "Wrong password or id");
			}
		}
		while(rs10.next()){//customer
			thisIsCustomer = true;
			String pin = txtPassword.getText();
			String DBPin = rs10.getString("pin");
			idFound = true;
			if(pin.equals(DBPin)){
				return true;

			}else{
				JOptionPane.showMessageDialog(null, "Wrong pin or id");
			}
		}
		if(idFound == false)
			JOptionPane.showMessageDialog(null, "Wrong password or id");
		return false;



	}

	public static Connection getConnection() throws Exception{
		//establish connection 
		//Scanner sc = new Scanner(System.in);
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/bank";
			//System.out.println("Enter username: ");
			String username = "root" ;// sc.nextLine().trim();               
			//System.out.println("Enter password: ");
			String password = "root" ;// sc.nextLine().trim();   
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("(Connected)");
			return conn;
		} catch(Exception e){System.out.println(e);} return null;
	}
	public static void withdraw(int id, double amount) throws SQLException{
		java.sql.PreparedStatement s = conn.prepareStatement("call withdraw ("+id+","+amount+");");
		s.execute();

	}
	public static void deposit(int id, double amount) throws SQLException{
		java.sql.PreparedStatement s = conn.prepareStatement("call deposit("+id+","+amount+");");
		s.executeQuery ();

	}
	public static void transfer(int fromID, int toID, double amount) throws SQLException{
		java.sql.PreparedStatement s = conn.prepareStatement("call transfer("+fromID+","+toID+","+amount+");");
		s.executeQuery ();
	}
	public static String getBalance(int id) throws SQLException{
		java.sql.PreparedStatement s = conn.prepareStatement("call get_balance("+id+");");
		s.executeQuery ();
		ResultSet rs = s.getResultSet ();
		while (rs.next ())
		{
			String balance = rs.getString("balance");
			return balance;

		}
		return "-1";
	}
	public static void deleteAccount(int id) throws SQLException{

		//java.sql.PreparedStatement s1 = conn.prepareStatement("DELETE FROM customers "
		//	+ "WHERE id = "+id+";");
		//s1.execute(); 
		java.sql.PreparedStatement s1 = conn.prepareStatement("DELETE FROM users "
				+ "WHERE id = "+id+";");
		s1.execute(); 
		// because users table is parent so s2 should be deleted first

	}
	public static void freezeAccount(int id) throws SQLException{
		java.sql.PreparedStatement s = conn.prepareStatement("UPDATE Customers  SET "
				+ "account_status = 'frozen' WHERE id = "+id+";");
		s.execute();

	}
	public static void unFreezeAccount(int id) throws SQLException{//test
		java.sql.PreparedStatement s = conn.prepareStatement("UPDATE Customers  SET "
				+ "account_status = Null WHERE id = "+id+";");
		s.execute();


	}
	/*
	private static void createUser(Connection c) {
	    System.out.print("Please print ID of new Customer: ");
	    int ID = in.nextInt();
	    System.out.print("Please print first name: ");
	    String fn = in.next();
	    System.out.print("Please print last name: ");
	    String ln = in.next();
	    System.out.print("Please print customer's pin: ");
	    int pin = in.nextInt();
	    System.out.print("Please print account number: ");
	    long acct = in.nextLong();
	    System.out.print("Please enter starting balance: ");
	    double bal = in.nextDouble();
	    System.out.print("If the customer has a street address, print it, or print XXX if not");
	    String addr = in.nextLine();
	    System.out.print("If the customer has a city, print it, or print XXX if not");
	    String city = in.next();
	    System.out.print("If the customer has a zip code, print it, or print XXX if not");
	    String zip = in.next();
	    System.out.print("If the customer has a country, print it, or print XXX if not");
	    String country = in.next();
	    System.out.print("If the customer has a region, print it, or print XXX if not");
	    String region = in.next();
	    System.out.print("If the customer has a salary, print it, or print XXX if not");
	    String salary = in.next();

	    String cols = "ID,first_name,last_name";
	    String vals = "" + ID + ", '" + fn + "', '" + ln + "'";
	    if (!addr.equals("XXX")) { cols = cols + ", street_address"; vals = vals + ", '" + addr + "'"; }
	    if (!city.equals("XXX")) { cols = cols + ", city"; vals = vals + ", '" + city + "'"; }
	    if (!zip.equals("XXX")) { cols = cols + ", zip"; vals = vals + ", '" + zip + "'"; }
	    if (!country.equals("XXX")) { cols = cols + ", country"; vals = vals + ", '" + country +"'"; }
	    if (!region.equals("XXX")) { cols = cols + ", region"; vals = vals + ", '" + region + "'"; }
	    if (!salary.equals("XXX")) { cols = cols + ", salary"; vals = vals + ", '" + salary +"'"; }

	    Statement stmt = null;
	    String sql = "INSERT INTO users (" + cols + ") VALUES (" + vals + ");";


	    try {
	      stmt = c.createStatement();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }

	    try {
	      stmt.executeUpdate(sql);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }

	    cols = "ID,pin,account_number,balance";
	    vals = "" + ID + "," +pin + "," + acct + "," + bal;
	    stmt = null;

	    sql = "INSERT INTO customers (" + cols + ") VALUES (" + vals + ");";

	    try {
	      stmt = c.createStatement();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }

	    try {
	      stmt.executeUpdate(sql);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	 */


	public static void createUser(int id, String first_name, String last_name) throws SQLException{

		java.sql.PreparedStatement s17 = conn.prepareStatement("INSERT INTO users (id,first_name,last_name) "
				+ "VALUES ("+id+",\'"+first_name+"\',\'"+last_name+"\');");
		s17.executeUpdate();


	}
	public static void updateUser(int UpdateId, String fieldName, String fieldValue) throws SQLException{

		java.sql.PreparedStatement s = conn.prepareStatement("UPDATE users set "+fieldName+" = '"+ fieldValue+"' where id = "+UpdateId+";");
		if(s.execute())
			System.out.println("user updated");


	}
	public static void updateCustomer(int UpdateId, String fieldName, String fieldValue) throws SQLException{

		java.sql.PreparedStatement s67 = conn.prepareStatement("UPDATE customers set "+fieldName+" = "+ fieldValue+" where id = "+UpdateId+";");
		if(s67.execute())
			System.out.println("user updated");


	}
	public static void createCustomerAccount(int id, int pin,				
			long account_number	,double balance ) throws SQLException{


		java.sql.PreparedStatement s23 = conn.prepareStatement("INSERT INTO customers (id,pin,"
				+ "account_number, balance)   VALUES ("+id+","+pin+", "+account_number+", "+balance+");" );
		s23.execute();

	}
	public boolean validID(int tempID) throws SQLException {
		if ((tempID + "").equals("")) return false;
		Statement stmt = null;
		String sql = "SELECT id FROM users WHERE id = " + tempID + ";";
		stmt = conn.createStatement();

		ResultSet res = null;
		res = stmt.executeQuery(sql);
		if(!res.next()) return false;
		int tID = res.getInt("id");
		if (tID == tempID) return true;

		return false;
	}
}
