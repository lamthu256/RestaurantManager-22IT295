package Restaurants;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.annotation.processing.Messager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JSpinner;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Admin {

	private JFrame frame;
	private DefaultTableModel model;//lưu trữ dữ liệu cho 1 bảng
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private DefaultTableModel model3;
	private DefaultTableModel model4;
	private DefaultTableModel model5;
	private Float result, sum = (float) 0;
	private JComboBox cb_idTable;
	private JComboBox cb_idStaff;
	private ResultSet rs;
	private JPanel p_chart;
	private ChartPanel chartPanel;
	private JFreeChart chart;
	private DefaultCategoryDataset dataset;
	
	public Admin() throws SQLException {
		initialize();
		LoadDataMain();
		LoadDataStaff();
		LoadDataFood();
		LoadDataTable();
		LoadDataBill();
		LoadDataRevenue();
		LoadDataCBStaff();
		LoadDataCBTable();
	}
	
	public void LoadDataMain()
	{
		try {
			ConnectionDB c = new ConnectionDB();
			List<Food> list = c.findFood();
			model.setRowCount(0);//Xoá các hàng trong bảng
			for(Food e : list) {
				model.addRow(new Object[] {
						e.getMaMA(), e.getTenMA(), e.getDONGIA()
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataBill()
	{
		try {
			ConnectionDB c = new ConnectionDB();
			List<Bill> list = c.findID();
			model1.setRowCount(0);
			for (Bill e : list)
			{
				 model1.addRow(new Object[] {
						 e.getMaHD(), e.getMaMA(), e.getTenMA(), e.getSoluong(), e.getDongia()
				 });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataStaff()
	{
		try {
			ConnectionDB c = new ConnectionDB();
			List<Staff> list = c.findStaff();
			model2.setRowCount(0);
			for(Staff e : list)
			{
				 model2.addRow(new Object[] {
						 e.getMaNV(), e.getHOTEN(), e.getCHUCVU()
				 });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataFood()
	{
		try {
			ConnectionDB c = new ConnectionDB();
			List<Food> list = c.findFood();
			model3.setRowCount(0);
			for (Food e : list)
			{
				 model3.addRow(new Object[] {
						 e.getMaMA(), e.getTenMA(), e.getDONGIA()
				 });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataTable()
	{
		try {
			ConnectionDB c = new ConnectionDB();
			List<Table> list = c.findTable();
			model4.setRowCount(0);
			for(Table e : list)
			{
				 model4.addRow(new Object[] {
						 e.getMaBA(), e.getTenBA(), e.getTRANGTHAI(), e.getSOCHONGOI()
				 });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataRevenue()
	{
		try {
			ConnectionDB c = new ConnectionDB();
			List<Revenue> list = c.findRevenue();
			model5.setRowCount(0);
			for(Revenue e : list)
			{
				 model5.addRow(new Object[] {
						 e.getMaHD(), e.getTenKH(), e.getMaBA(), e.getMaNV(), e.getNgay(), e.getTongtien()
				 });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataCBStaff(){
		try {
			cb_idStaff.removeAllItems();
			cb_idStaff.setModel(new DefaultComboBoxModel(new String[] {""}));
			ConnectionDB c = new ConnectionDB();
			rs = c.loadCBStaff();
			while(rs.next())
			{
				cb_idStaff.addItem(rs.getString("MaNV"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataCBTable() {
		try {
			cb_idTable.removeAllItems();
			cb_idTable.setModel(new DefaultComboBoxModel(new String[] {""}));
			ConnectionDB c = new ConnectionDB();
			rs = c.loadCBTable();
			while(rs.next())
			{
				cb_idTable.addItem(rs.getString("MaBA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public CategoryDataset LoadDataChart() {
		dataset = new DefaultCategoryDataset();
		try {
			ConnectionDB c = new ConnectionDB();
			List<Chart> list = c.freeChart();
			for(Chart ch : list) {
				dataset.setValue(ch.getTongtien(), "Đồng", ch.getNgay());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return dataset;
    }
	
	public JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống Kê Doanh Thu",
                "",
                "Đồng(VND)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBackground(new Color(240, 241, 242));
		frame.setBounds(0, 0, 814, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
				layeredPane.setLayout(new CardLayout(0, 0));
				
	    //Panel main

		JPanel panel = new JPanel();
		panel.setBackground(new Color(250, 203, 135));
		layeredPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
				
		JPanel p_main = new JPanel();
		p_main.setBackground(new Color(250, 203, 135));
	    p_main.setBounds(6, 0, 327, 366);
		panel.add(p_main);
		p_main.setLayout(null);
				
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(250, 203, 135));
		panel_1.setBounds(331, 0, 483, 366);
		panel.add(panel_1);
		panel_1.setLayout(null);
				
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 241, 242));
		panel_2.setBounds(6, 6, 471, 59);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
				
		JLabel lb_mIdbill = new JLabel("Mã hóa đơn");
		lb_mIdbill.setForeground(new Color(128, 0, 0));
		lb_mIdbill.setBackground(new Color(0, 128, 0));
		lb_mIdbill.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_mIdbill.setBounds(6, 6, 84, 16);
		panel_2.add(lb_mIdbill);
				
		JLabel lbmain_cusname = new JLabel("Tên khách hàng");
		lbmain_cusname.setForeground(new Color(128, 0, 0));
		lbmain_cusname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbmain_cusname.setBounds(6, 37, 102, 16);
		panel_2.add(lbmain_cusname);
				
		JTextField tf_mIdbill = new JTextField();
		tf_mIdbill.setBackground(new Color(255, 255, 255));
		tf_mIdbill.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tf_mIdbill.setBounds(114, 1, 164, 26);
		panel_2.add(tf_mIdbill);
		tf_mIdbill.setColumns(10);
				
		JTextField tfmain_cusname = new JTextField();
		tfmain_cusname.setBackground(new Color(255, 255, 255));
		tfmain_cusname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfmain_cusname.setColumns(10);
		tfmain_cusname.setBounds(114, 32, 164, 26);
		panel_2.add(tfmain_cusname);
				
		JLabel lbmain_idstaff = new JLabel("Mã NV");
		lbmain_idstaff.setForeground(new Color(128, 0, 0));
		lbmain_idstaff.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbmain_idstaff.setBounds(290, 6, 40, 16);
		panel_2.add(lbmain_idstaff);
		
		cb_idStaff = new JComboBox();
		cb_idStaff.setBounds(363, 2, 102, 27);
		panel_2.add(cb_idStaff);		
		
		JLabel lbmain_idtable = new JLabel("Mã bàn ăn");
		lbmain_idtable.setForeground(new Color(128, 0, 0));
		lbmain_idtable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbmain_idtable.setBounds(290, 37, 73, 16);
		panel_2.add(lbmain_idtable);
		
		cb_idTable = new JComboBox();
		cb_idTable.setBounds(363, 33, 102, 27);
		panel_2.add(cb_idTable);
				
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 241, 242));
		panel_3.setBounds(6, 68, 471, 55);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
				
		JLabel lb_mId = new JLabel("Mã món ăn");
		lb_mId.setForeground(new Color(128, 0, 0));
		lb_mId.setBackground(new Color(219, 74, 77));
		lb_mId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_mId.setBounds(6, 6, 70, 16);
		panel_3.add(lb_mId);
		
		JTextField tf_mId = new JTextField();
		tf_mId.setBackground(new Color(255, 255, 255));
		tf_mId.setEditable(false);
		tf_mId.setBounds(82, 1, 80, 26);
		panel_3.add(tf_mId);
		tf_mId.setColumns(10);
				
		JLabel lb_mName = new JLabel("Tên món ăn");
		lb_mName.setForeground(new Color(128, 0, 0));
		lb_mName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_mName.setBounds(167, 6, 70, 16);
		panel_3.add(lb_mName);
				
		JTextField tf_mName = new JTextField();
		tf_mName.setBackground(new Color(255, 255, 255));
		tf_mName.setEditable(false);
		tf_mName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tf_mName.setBounds(246, 1, 130, 26);
		panel_3.add(tf_mName);
		tf_mName.setColumns(10);
		
		JLabel lb_mAmount = new JLabel("Số lượng");
		lb_mAmount.setForeground(new Color(128, 0, 0));
		lb_mAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_mAmount.setBounds(6, 32, 70, 16);
		panel_3.add(lb_mAmount);
		
		JTextField tf_mAmount = new JTextField();
		tf_mAmount.setBackground(new Color(255, 255, 255));
		tf_mAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tf_mAmount.setColumns(10);
		tf_mAmount.setBounds(82, 27, 80, 26);
		panel_3.add(tf_mAmount);
		
		JLabel lb_mPrice = new JLabel("Đơn giá");
		lb_mPrice.setForeground(new Color(128, 0, 0));
		lb_mPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_mPrice.setBounds(177, 32, 52, 16);
		panel_3.add(lb_mPrice);
		
		JTextField tf_mPrice = new JTextField();
		tf_mPrice.setBackground(new Color(255, 255, 255));
		tf_mPrice.setEditable(false);
		tf_mPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tf_mPrice.setColumns(10);
		tf_mPrice.setBounds(246, 27, 130, 26);
		panel_3.add(tf_mPrice);
		
		JTable tb_main = new JTable();
		tb_main.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tb_main.getSelectedRow();
					if(row >= 0)
					{
						int id = (int)tb_main.getValueAt(row, 0);
						ConnectionDB c = new ConnectionDB();
						Food f = c.findbyIdFood(id);
						
						if(f != null)
						{
							tf_mId.setText(String.valueOf(f.getMaMA()));
							tf_mName.setText(f.getTenMA());
							tf_mPrice.setText(String.valueOf(f.getDONGIA()));
						}
					}
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		});
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"ID", "Tên món ăn", "Đơn giá"} );
		tb_main.setModel(model);
			
	    JScrollPane sp_main = new JScrollPane();
		sp_main.setBounds(0, 6, 327, 199);
		p_main.add(sp_main);
		sp_main.setViewportView(tb_main);
		
		JTable tb_bill = new JTable();
		model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(new String[] { "Mã hóa đơn", "Mã món ăn", "Tên món ăn", "Số lượng", "Đơn giá"} );
		tb_bill.setModel(model1);
		
		JScrollPane sp_bill = new JScrollPane(tb_bill);
		sp_bill.setBounds(6, 126, 471, 171);
		panel_1.add(sp_bill);
		sp_bill.setViewportView(tb_bill);		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 241, 242));
		panel_4.setBounds(6, 301, 471, 59);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
				
		JTextField tf_total = new JTextField();
		tf_total.setBackground(new Color(255, 255, 255));
		tf_total.setBounds(187, 21, 148, 37);
		panel_4.add(tf_total);
		tf_total.setColumns(10);
		
		JLabel lb_mImage = new JLabel("");
		lb_mImage.setIcon(new ImageIcon(new ImageIcon("/Users/lamthu/Downloads/Picture3.png")
				.getImage().getScaledInstance(327, 152, Image.SCALE_DEFAULT)));
		lb_mImage.setBounds(0, 208, 327, 152);
		p_main.add(lb_mImage);
		
		JButton btmain_add = new JButton("Thêm");
		btmain_add.setBorderPainted(false);
		btmain_add.setOpaque(true);
		btmain_add.setForeground(new Color(250, 203, 135));
		btmain_add.setBackground(new Color(219, 74, 77));
		btmain_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Bill b = new Bill();
					b.setMaHD(Integer.valueOf(tf_mIdbill.getText()));
					b.setMaMA(Integer.valueOf(tf_mId.getText()));
					b.setTenMA(tf_mName.getText());
					b.setSoluong(Integer.valueOf(tf_mAmount.getText()));
					b.setDongia(Float.valueOf(tf_mPrice.getText()));
			
					result = Float.parseFloat(tf_mPrice.getText()) * Integer.parseInt(tf_mAmount.getText());
					sum += result;
					tf_total.setText(String.valueOf(sum));
					
					ConnectionDB c = new ConnectionDB();
					c.insertBill(b);	
					LoadDataBill();
				} catch (Exception e2) {
					e2.printStackTrace();
					result = (float) 0; sum = (float) 0;
					JOptionPane.showMessageDialog(btmain_add, "Lỗi");
				}finally {
					tf_mId.setText("");
					tf_mName.setText("");
				    tf_mPrice.setText("");
				    tf_mAmount.setText("");
				}
			}
		});
		btmain_add.setFont(new Font("Tahoma", Font.BOLD, 12));
		btmain_add.setBounds(385, 22, 80, 26);
		panel_3.add(btmain_add);
		
		JLabel lb_total = new JLabel("Thành tiền");
		lb_total.setForeground(new Color(128, 0, 0));
		lb_total.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb_total.setBounds(223, 6, 81, 16);
		panel_4.add(lb_total);
	
		JDateChooser dateChooser = new JDateChooser();
		java.util.Date date = new java.util.Date();
		dateChooser.setDate(date);
		dateChooser.setBackground(new Color(250, 203, 135));
		dateChooser.setBounds(6, 23, 178, 30);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		panel_4.add(dateChooser);
		
		JButton bt_pay = new JButton("Thanh toán");
		bt_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Revenue r = new Revenue();
				    r.setMaHD(Integer.valueOf(tf_mIdbill.getText()));
				    r.setTenKH(tfmain_cusname.getText()); 
				    r.setMaNV(Integer.valueOf(String.valueOf(cb_idStaff.getSelectedItem())));
				    r.setMaBA(Integer.valueOf(String.valueOf(cb_idTable.getSelectedItem())));
				    r.setNgay(((JTextComponent) dateChooser.getDateEditor().getUiComponent()).getText());
					r.setTongtien(Float.valueOf(tf_total.getText()));
					
					Table t = new Table();
					t.setMaBA(Integer.valueOf(String.valueOf(cb_idTable.getSelectedItem())));
					t.setTenBA("Chưa cập nhập");
					t.setTRANGTHAI("Trống");
					t.setSOCHONGOI(0);
					
					ConnectionDB c = new ConnectionDB();
					c.insertRevenue(r);
					c.updateTable(t);
					
					p_chart = new JPanel();
					p_chart.setForeground(new Color(219, 74, 77));
					p_chart.setBackground(new Color(250, 203, 135));
					layeredPane.add(p_chart, "name_26109651612696");
					
					CategoryDataset dataset = LoadDataChart();
					
			        chart = createChart(dataset);
			        p_chart.setLayout(null);
			        chartPanel = new ChartPanel(chart);
			        chartPanel.setBackground(new Color(255, 255, 255));
			        chartPanel.setForeground(new Color(0, 0, 0));
			        chartPanel.setDisplayToolTips(true);
			        chartPanel.setBounds(6, 6, 802, 354);
			        chartPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
			        p_chart.add(chartPanel);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btmain_add, "Lỗi");
					e2.printStackTrace();
				}finally {
					tf_mIdbill.setText("");
					tfmain_cusname.setText("");
					tf_total.setText("");
					LoadDataTable();
					LoadDataCBTable();
					LoadDataRevenue();
					result = (float) 0; sum = (float) 0;
				}
			}
		});
		bt_pay.setBackground(new Color(219, 74, 77));
		bt_pay.setForeground(new Color(250, 203, 135));
		bt_pay.setBorderPainted(false);
		bt_pay.setOpaque(true);
		bt_pay.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_pay.setBounds(338, 25, 127, 30);
		panel_4.add(bt_pay);
		
		//Panel Staff
				
		JPanel p_staff = new JPanel();
		p_staff.setBackground(new Color(250, 203, 135));
		layeredPane.add(p_staff, "name_280819096080963");
		p_staff.setLayout(null);
		p_staff.setVisible(false);
		
		JPanel p_sName = new JPanel();
		p_sName.setBackground(new Color(240, 241, 242));
		p_sName.setBounds(377, 86, 431, 68);
		p_staff.add(p_sName);
		p_sName.setLayout(null);
				
		JPanel p_sId = new JPanel();
		p_sId.setBackground(new Color(240, 241, 242));
		p_sId.setBounds(377, 6, 431, 68);
		p_staff.add(p_sId);
		p_sId.setLayout(null);
				
		JLabel lb_id = new JLabel("ID: ");
		lb_id.setForeground(new Color(128, 0, 0));
		lb_id.setBackground(new Color(238, 237, 238));
		lb_id.setBounds(16, 25, 35, 19);
		lb_id.setFont(new Font("Tahoma", Font.BOLD, 15));
		p_sId.add(lb_id);
				
		JTextField tf_id = new JTextField();
		tf_id.setBackground(new Color(255, 255, 255));
		tf_id.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_id.setBounds(149, 14, 268, 41);
		p_sId.add(tf_id);
				
		JPanel p_sUsertype = new JPanel();
		p_sUsertype.setBackground(new Color(240, 241, 242));
		p_sUsertype.setBounds(377, 167, 431, 68);
		p_staff.add(p_sUsertype);
		p_sUsertype.setLayout(null);
				
		JLabel lb_name = new JLabel("Tên Nhân Viên:");
		lb_name.setForeground(new Color(128, 0, 0));
		lb_name.setBackground(new Color(238, 237, 238));
		lb_name.setBounds(16, 25, 121, 19);
		lb_name.setFont(new Font("Tahoma", Font.BOLD, 15));
		p_sName.add(lb_name);
				
		JTextField tf_name = new JTextField();
		tf_name.setBackground(new Color(255, 255, 255));
		tf_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_name.setBounds(149, 14, 270, 41);
		p_sName.add(tf_name);
				
		JLabel lb_position = new JLabel("Chức Vụ:");
		lb_position.setForeground(new Color(128, 0, 0));
		lb_position.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_position.setBounds(16, 19, 68, 30);
		p_sUsertype.add(lb_position);
				
		JTextField tf_position = new JTextField();
		tf_position.setBackground(new Color(255, 255, 255));
		tf_position.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_position.setBounds(149, 14, 270, 41);
		p_sUsertype.add(tf_position);
				
		JTable tb_staff = new JTable();
		tb_staff.setBackground(new Color(255, 255, 255));
		tb_staff.setForeground(new Color(0, 0, 0));
		tb_staff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tb_staff.getSelectedRow();
					if(row >= 0)
					{
						int id = (int) tb_staff.getValueAt(row, 0);
						ConnectionDB c = new ConnectionDB();
						Staff s = c.findbyIdStaff(id);
						
						if(s != null)
						{
							tf_id.setText(String.valueOf(s.getMaNV()));
							tf_name.setText(s.getHOTEN());
							tf_position.setText(s.getCHUCVU());
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(new String[] {"ID", "Tên nhân viên", "Chức vụ"} );
		tb_staff.setModel(model2);
			
		JScrollPane scrollPane_staff = new JScrollPane(tb_staff);
		scrollPane_staff.setBounds(6, 6, 365, 193);
		p_staff.add(scrollPane_staff);
		scrollPane_staff.setViewportView(tb_staff);	
		
		JButton bt_sAdd = new JButton("Thêm");
		bt_sAdd.setForeground(new Color(250, 203, 135));
		bt_sAdd.setBackground(new Color(219, 74, 77));
		bt_sAdd.setBorderPainted(false);
		bt_sAdd.setOpaque(true);
		bt_sAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Staff s = new Staff();
					s.setMaNV(Integer.valueOf(tf_id.getText()));
					s.setHOTEN(tf_name.getText());
					s.setCHUCVU(tf_position.getText());
					
					ConnectionDB c = new ConnectionDB();
					c.insertStaff(s);
					LoadDataStaff();
					LoadDataCBStaff();
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(bt_sAdd, "Lỗi");
				} finally {
					tf_id.setText("");
					tf_name.setText("");
					tf_position.setText("");
				}
			}
		});
		bt_sAdd.setIcon(new ImageIcon("/Users/lamthu/Downloads/Male-user-add-icon.png"));
		bt_sAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_sAdd.setBounds(408, 257, 107, 36);
		p_staff.add(bt_sAdd);
				
		JButton bt_sEdit = new JButton("Sửa");
		bt_sEdit.setForeground(new Color(245, 175, 125));
		bt_sEdit.setBackground(new Color(219, 74, 77));
		bt_sEdit.setBorderPainted(false);
		bt_sEdit.setOpaque(true);
		bt_sEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Staff s = new Staff();
					s.setMaNV(Integer.valueOf(tf_id.getText()));
					s.setHOTEN(tf_name.getText());
					s.setCHUCVU(tf_position.getText());
					
					ConnectionDB c = new ConnectionDB();
					c.updateStaff(s);
					LoadDataStaff();
					LoadDataCBStaff();
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					tf_id.setText("");
					tf_name.setText("");
					tf_position.setText("");
				}
			}
		});
		bt_sEdit.setIcon(new ImageIcon("/Users/lamthu/Downloads/Sketch-Book-icon.png"));
		bt_sEdit.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_sEdit.setBounds(541, 257, 107, 36);
		p_staff.add(bt_sEdit);
				
		JButton bt_sDelete = new JButton("Xóa");
		bt_sDelete.setForeground(new Color(245, 175, 125));
		bt_sDelete.setBackground(new Color(219, 74, 77));
		bt_sDelete.setBorderPainted(false);
		bt_sDelete.setOpaque(true);
		bt_sDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(bt_sDelete, "Bạn có muốn xóa nhân viên không?") == JOptionPane.NO_OPTION) {
				    return;
				}
				try {
					ConnectionDB c = new ConnectionDB();
					c.deleteStaff(Integer.valueOf(tf_id.getText()));
					LoadDataStaff();
					LoadDataCBStaff();
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					tf_id.setText("");
					tf_name.setText("");
					tf_position.setText("");
				}
			}
		});
		bt_sDelete.setIcon(new ImageIcon("/Users/lamthu/Downloads/red-cross-icon.png"));
		bt_sDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bt_sDelete.setBounds(673, 257, 107, 36);
		p_staff.add(bt_sDelete);
				
		JTextField tf_sFind = new JTextField();
		tf_sFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tf_sFind.setText("");
			}
		});
		tf_sFind.setText("Nhập mã nhân viên:");
		tf_sFind.setBackground(new Color(255, 255, 255));
		tf_sFind.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_sFind.setColumns(10);
		tf_sFind.setBounds(397, 307, 264, 41);
		p_staff.add(tf_sFind);
				
		JButton bt_sFind = new JButton("Tìm");
		bt_sFind.setForeground(new Color(250, 203, 135));
		bt_sFind.setBackground(new Color(219, 74, 77));
		bt_sFind.setBorderPainted(false);
		bt_sFind.setOpaque(true);
		bt_sFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConnectionDB c = new ConnectionDB();
					Staff s = c.findbyIdStaff(Integer.valueOf(tf_sFind.getText()));
					if(s != null){
						tf_id.setText(String.valueOf(s.getMaNV()));
						tf_name.setText(s.getHOTEN());
						tf_position.setText(s.getCHUCVU());
					}
					else{
						JOptionPane.showMessageDialog(bt_sFind, "Không tìm thấy nhân viên!!");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					tf_id.setText("");
					tf_name.setText("");
					tf_position.setText("");
					JOptionPane.showMessageDialog(bt_sFind, "Không tìm thấy nhân viên!!");
				} finally {
					tf_sFind.setText("Nhập mã nhân viên:");
				}
			}
		});
		bt_sFind.setIcon(new ImageIcon("/Users/lamthu/Downloads/Magnifier-icon.png"));
		bt_sFind.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_sFind.setBounds(673, 310, 107, 36);
		p_staff.add(bt_sFind);
		
		JLabel lb_sImage = new JLabel("");
		lb_sImage.setIcon(new ImageIcon(new ImageIcon("/Users/lamthu/Downloads/Picture5.png")
				.getImage().getScaledInstance(365, 156, Image.SCALE_DEFAULT)));
		lb_sImage.setBounds(6, 204, 365, 156);
		p_staff.add(lb_sImage);
		
	    //Panel Food
				
	    JPanel p_food = new JPanel();
	    p_food.setBackground(new Color(250, 203, 135));
	    layeredPane.add(p_food, "name_280819112336493");
	    p_food.setLayout(null);
	    p_food.setVisible(false);		
	    
		JPanel p_fId = new JPanel();
		p_fId.setBackground(new Color(240, 241, 242));
		p_fId.setBounds(375, 6, 431, 68);
		p_food.add(p_fId);
		p_fId.setLayout(null);
		
		JPanel p_fName = new JPanel();
		p_fName.setBackground(new Color(240, 241, 242));
		p_fName.setBounds(375, 83, 431, 68);
		p_food.add(p_fName);
		p_fName.setLayout(null);
		
		JPanel p_fPrice = new JPanel();
		p_fPrice.setBackground(new Color(240, 241, 242));
		p_fPrice.setBounds(375, 160, 431, 68);
		p_food.add(p_fPrice);
		p_fPrice.setLayout(null);
		
		JLabel lb_fId = new JLabel("ID: ");
		lb_fId.setForeground(new Color(128, 0, 0));
		lb_fId.setBounds(18, 25, 27, 19);
		lb_fId.setFont(new Font("Tahoma", Font.BOLD, 15));
		p_fId.add(lb_fId);
		
		JTextField tf_fId = new JTextField();
		tf_fId.setBackground(new Color(255, 255, 255));
		tf_fId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_fId.setBounds(124, 14, 295, 41);
		p_fId.add(tf_fId);
		
		JLabel lb_fName = new JLabel("Tên Món Ăn:");
		lb_fName.setForeground(new Color(128, 0, 0));
		lb_fName.setBounds(18, 19, 93, 30);
		lb_fName.setFont(new Font("Tahoma", Font.BOLD, 15));
		p_fName.add(lb_fName);
		
		JTextField tf_fName = new JTextField();
		tf_fName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_fName.setBounds(124, 14, 295, 41);
		p_fName.add(tf_fName);
		
		JLabel lb_fPrice = new JLabel("Đơn Giá:");
		lb_fPrice.setForeground(new Color(128, 0, 0));
		lb_fPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_fPrice.setBounds(18, 18, 70, 30);
		p_fPrice.add(lb_fPrice);
		
		JTextField tf_fPrice = new JTextField();
		tf_fPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_fPrice.setBounds(124, 14, 293, 41);
		p_fPrice.add(tf_fPrice);
		
		JTable tb_food = new JTable();
		tb_food.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tb_food.getSelectedRow();
					if(row >= 0)
					{
						int id = (int) tb_food.getValueAt(row, 0);
						ConnectionDB c = new ConnectionDB();
						Food f = c.findbyIdFood(id);
						
						if(f != null)
						{
							tf_fId.setText(String.valueOf(f.getMaMA()));
							tf_fName.setText(f.getTenMA());
							tf_fPrice.setText(String.valueOf(f.getDONGIA()));
						}
					}
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		});
		model3 = new DefaultTableModel();
		model3.setColumnIdentifiers(new String[] {"ID", "Tên món ăn", "Đơn giá"} );	
		tb_food.setModel(model3);
		
		JScrollPane scrollPane_food = new JScrollPane(tb_food);
		scrollPane_food.setBounds(6, 6, 365, 180);
		p_food.add(scrollPane_food);
		scrollPane_food.setViewportView(tb_food);	
		
		JButton bt_fAdd = new JButton("Thêm");
		bt_fAdd.setBackground(new Color(219, 74, 77));
		bt_fAdd.setForeground(new Color(250, 203, 135));
		bt_fAdd.setBorderPainted(false);
		bt_fAdd.setOpaque(true);
		bt_fAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Food f = new Food();
					f.setMaMA(Integer.valueOf(tf_fId.getText()));
					f.setTenMA(tf_fName.getText());
					f.setDONGIA(Float.valueOf(tf_fPrice.getText()));
					
					ConnectionDB c = new ConnectionDB();
					c.insertFood(f);
					LoadDataFood();
					LoadDataMain();
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(bt_fAdd, "Lỗi");
				} finally {
					tf_fId.setText("");
					tf_fName.setText("");
					tf_fPrice.setText("");
				}
			}
		});
		bt_fAdd.setIcon(new ImageIcon("/Users/lamthu/Downloads/add-icon (1).png"));
		bt_fAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_fAdd.setBounds(396, 250, 107, 36);
		p_food.add(bt_fAdd);
		
		JButton bt_fEdit = new JButton("Sửa");
		bt_fEdit.setBackground(new Color(219, 74, 77));
		bt_fEdit.setForeground(new Color(250, 203, 135));
		bt_fEdit.setBorderPainted(false);
		bt_fEdit.setOpaque(true);
		bt_fEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Food f = new Food();
					f.setMaMA(Integer.valueOf(tf_fId.getText()));
					f.setTenMA(tf_fName.getText());
					f.setDONGIA(Float.valueOf(tf_fPrice.getText()));
					
					ConnectionDB c = new ConnectionDB();
					c.updateFood(f);
					tf_fId.setText("");
					tf_fName.setText("");
					tf_fPrice.setText("");
					LoadDataFood();
					LoadDataMain();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		bt_fEdit.setIcon(new ImageIcon("/Users/lamthu/Downloads/Sketch-Book-icon.png"));
		bt_fEdit.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_fEdit.setBounds(538, 251, 107, 36);
		p_food.add(bt_fEdit);
		
		JButton bt_fDelete = new JButton("Xóa");
		bt_fDelete.setBackground(new Color(219, 74, 77));
		bt_fDelete.setForeground(new Color(250, 203, 135));
		bt_fDelete.setBorderPainted(false);
		bt_fDelete.setOpaque(true);
		bt_fDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(bt_fDelete, "Bạn có muốn xóa món ăn không?") == 
						JOptionPane.NO_OPTION) {
				    return;
				}
				try {
					ConnectionDB c = new ConnectionDB();
					c.deleteFood(Integer.valueOf(tf_fId.getText()));
					tf_fId.setText("");
					tf_fName.setText("");
					tf_fPrice.setText("");
					LoadDataFood();
					LoadDataMain();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		bt_fDelete.setIcon(new ImageIcon("/Users/lamthu/Downloads/red-cross-icon.png"));
		bt_fDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_fDelete.setBounds(679, 250, 107, 36);
		p_food.add(bt_fDelete);
		
		JTextField tf_fFind = new JTextField();
		tf_fFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tf_fFind.setText("");
			}
		});
		tf_fFind.setForeground(new Color(0, 0, 0));
		tf_fFind.setText("Nhập mã món ăn:");
		tf_fFind.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_fFind.setColumns(10);
		tf_fFind.setBounds(386, 307, 270, 41);
		p_food.add(tf_fFind);
		
		JButton bt_fFind = new JButton("Tìm");	
		bt_fFind.setBackground(new Color(219, 74, 77));
		bt_fFind.setForeground(new Color(250, 203, 135));
		bt_fFind.setBorderPainted(false);
		bt_fFind.setOpaque(true);
		bt_fFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConnectionDB c = new ConnectionDB();
					Food f = c.findbyIdFood(Integer.valueOf(tf_fFind.getText()));
					if(f != null)
					{
						tf_fId.setText(String.valueOf(f.getMaMA()));
						tf_fName.setText(f.getTenMA());
		                tf_fPrice.setText(String.valueOf(f.getDONGIA()));
					}
					else {
						JOptionPane.showMessageDialog(bt_fFind, "Không tìm thấy món ăn!!");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(bt_fFind, "Không tìm thấy món ăn!!");
					tf_fId.setText("");
					tf_fName.setText("");
	                tf_fPrice.setText("");
				} finally {
					tf_fFind.setText("Nhập mã món ăn:");
				}
			}
		});
		bt_fFind.setIcon(new ImageIcon("/Users/lamthu/Downloads/Magnifier-icon.png"));
		bt_fFind.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_fFind.setBounds(679, 310, 107, 36);						
		p_food.add(bt_fFind);
		
		JLabel lb_fImage = new JLabel("");
		lb_fImage.setIcon(new ImageIcon(new ImageIcon("/Users/lamthu/Downloads/Picture1.png")
				.getImage().getScaledInstance(365, 168, Image.SCALE_DEFAULT)));
		lb_fImage.setBounds(6, 192, 365, 168);
		p_food.add(lb_fImage);
		
		//Panel Table
		
		JPanel p_table = new JPanel();
		p_table.setBackground(new Color(250, 203, 135));
		layeredPane.add(p_table, "name_280819130513083");
		p_table.setLayout(null);
		p_table.setVisible(false);
		
		JPanel p_tId = new JPanel();
		p_tId.setBackground(new Color(240, 241, 242));
		p_tId.setBounds(383, 6, 427, 51);
		p_table.add(p_tId);
		p_tId.setLayout(null);
		
		JPanel p_tName = new JPanel();
		p_tName.setBackground(new Color(240, 241, 242));
		p_tName.setBounds(383, 69, 427, 51);
		p_tName.setLayout(null);
		p_table.add(p_tName);
		
		JPanel p_status = new JPanel();
		p_status.setBackground(new Color(240, 241, 242));
		p_status.setBounds(383, 132, 427, 51);
		p_status.setLayout(null);
		p_table.add(p_status);
		
		JPanel p_num = new JPanel();
		p_num.setBackground(new Color(240, 241, 242));
		p_num.setBounds(383, 195, 427, 51);
		p_num.setLayout(null);
		p_table.add(p_num);
		
		JLabel lb_tId = new JLabel("ID:");
		lb_tId.setForeground(new Color(128, 0, 0));
		lb_tId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_tId.setBounds(23, 18, 61, 16);
		p_tId.add(lb_tId);
		
		JTextField tf_tId = new JTextField();
		tf_tId.setBounds(128, 9, 293, 34);
		p_tId.add(tf_tId);
		tf_tId.setColumns(10);
		
		JLabel lb_tName = new JLabel("Tên Bàn:");
		lb_tName.setForeground(new Color(128, 0, 0));
		lb_tName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_tName.setBounds(23, 18, 61, 16);
		p_tName.add(lb_tName);
		
		JTextField tf_tName = new JTextField();
		tf_tName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tf_tName.setText("");
			}
		});
		tf_tName.setColumns(10);
		tf_tName.setBounds(128, 9, 293, 34);
		p_tName.add(tf_tName);
		
		JLabel ld_status = new JLabel("Trạng Thái:");
		ld_status.setForeground(new Color(128, 0, 0));
		ld_status.setFont(new Font("Tahoma", Font.BOLD, 13));
		ld_status.setBounds(23, 18, 88, 16);
		p_status.add(ld_status);
		
		JTextField tf_status = new JTextField();
		tf_status.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tf_status.setText("");
			}
		});
		tf_status.setColumns(10);
		tf_status.setBounds(128, 9, 293, 34);
		p_status.add(tf_status);
		
		JLabel lb_numofseat = new JLabel("Số Chỗ:");
		lb_numofseat.setForeground(new Color(128, 0, 0));
		lb_numofseat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_numofseat.setBounds(23, 18, 61, 16);
		p_num.add(lb_numofseat);
		
		JTextField tf_numofseat = new JTextField();
		tf_numofseat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tf_numofseat.setText("");
			}
		});
		tf_numofseat.setColumns(10);
		tf_numofseat.setBounds(128, 9, 293, 34);
		p_num.add(tf_numofseat);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(128, 13, 293, 26);
		p_num.add(spinner);
		
		JTable tb_table = new JTable();
		tb_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tb_table.getSelectedRow();
					if(row >= 0)
					{
						int id = (int) tb_table.getValueAt(row, 0);
						ConnectionDB c = new ConnectionDB();
						Table t = c.findbyIdTable(id);
						
						if(t != null)
						{
							tf_tId.setText(String.valueOf(t.getMaBA()));
							tf_tName.setText(String.valueOf(t.getTenBA()));
							tf_status.setText(t.getTRANGTHAI());
							tf_numofseat.setText(String.valueOf(t.getSOCHONGOI()));
						}
					}
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		});
		model4 = new DefaultTableModel();
		model4.setColumnIdentifiers(new String[] {"ID", "Tên bàn", "Trạng thái", "Số chỗ ngồi"} );
		tb_table.setModel(model4);
		
		JScrollPane scrollPane_table = new JScrollPane(tb_table);
		scrollPane_table.setBounds(6, 6, 371, 157);
		p_table.add(scrollPane_table);
		
	    JButton bt_tAdd = new JButton("Thêm");
	    bt_tAdd.setBackground(new Color(219, 74, 77));
	    bt_tAdd.setForeground(new Color(250, 203, 135));
	    bt_tAdd.setBorderPainted(false);
		bt_tAdd.setOpaque(true);
	    bt_tAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Table t = new Table();
					t.setMaBA(Integer.valueOf(tf_tId.getText()));
					t.setTenBA(tf_tName.getText());
					t.setTRANGTHAI(tf_status.getText());
					t.setSOCHONGOI(Integer.valueOf(tf_numofseat.getText()));
					
					ConnectionDB c = new ConnectionDB();
					c.insertTable(t);
					LoadDataTable();
					LoadDataCBTable();
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(bt_tAdd, "Lỗi");
				} finally {
					tf_tId.setText("");
					tf_tName.setText("");
					tf_status.setText("");
					tf_numofseat.setText("");
				}
			}
		});
	    bt_tAdd.setBounds(406, 261, 107, 36);
	    bt_tAdd.setIcon(new ImageIcon("/Users/lamthu/Downloads/add-icon (1).png"));
	    bt_tAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
	    p_table.add(bt_tAdd);
	    
		JButton bt_tEdit = new JButton("Sửa");
		bt_tEdit.setBackground(new Color(219, 74, 77));
		bt_tEdit.setForeground(new Color(250, 203, 135));
		bt_tEdit.setBounds(543, 261, 107, 36);
		bt_tEdit.setBorderPainted(false);
		bt_tEdit.setOpaque(true);
		bt_tEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Table t = new Table();
					t.setMaBA(Integer.valueOf(tf_tId.getText()));
					t.setTenBA(tf_tName.getText());
					t.setTRANGTHAI(tf_status.getText());
					t.setSOCHONGOI(Integer.valueOf(tf_numofseat.getText()));
					
					ConnectionDB c = new ConnectionDB();
					c.updateTable(t);
					tf_tId.setText("");
					tf_tName.setText("");
					tf_status.setText("");
					tf_numofseat.setText("");
					LoadDataTable();
					LoadDataCBTable();
				} catch (Exception e2) {
					e2.printStackTrace();
			    }
		    }
		});
		bt_tEdit.setIcon(new ImageIcon("/Users/lamthu/Downloads/Sketch-Book-icon.png"));
		bt_tEdit.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_table.add(bt_tEdit);
		
		JButton bt_tDelete = new JButton("Xóa");
		bt_tDelete.setBackground(new Color(219, 74, 77));
		bt_tDelete.setForeground(new Color(250, 203, 135));
		bt_tDelete.setBounds(679, 261, 107, 36);
		bt_tDelete.setBorderPainted(false);
		bt_tDelete.setOpaque(true);
		bt_tDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(bt_fDelete, "Bạn có muốn xóa bàn ăn không?") 
						== JOptionPane.NO_OPTION) {
				    return;
				}
				try {
					ConnectionDB c = new ConnectionDB();
					c.deleteTable(Integer.valueOf(tf_tId.getText()));
					tf_tId.setText("");
					tf_tName.setText("");
					tf_status.setText("");
					tf_numofseat.setText("");
					LoadDataTable();
					LoadDataCBTable();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		bt_tDelete.setIcon(new ImageIcon("/Users/lamthu/Downloads/red-cross-icon.png"));
		bt_tDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_table.add(bt_tDelete);
		
		JTextField tf_tFind = new JTextField();
		tf_tFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tf_tFind.setText("");
			}
		});
		tf_tFind.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_tFind.setText("Nhập mã bàn:");
		tf_tFind.setBounds(395, 315, 268, 41);
		tf_tFind.setColumns(10);
		p_table.add(tf_tFind);
		
		JButton bt_tFind = new JButton("Tìm");
		bt_tFind.setBackground(new Color(219, 74, 77));
		bt_tFind.setForeground(new Color(250, 203, 135));
		bt_tFind.setBounds(679, 317, 107, 36);
		bt_tFind.setBorderPainted(false);
		bt_tFind.setOpaque(true);
		bt_tFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConnectionDB c = new ConnectionDB();
					Table t = c.findbyIdTable(Integer.valueOf(tf_tFind.getText()));
					if(t != null)
					{
						tf_tId.setText(String.valueOf(t.getMaBA()));
						tf_tName.setText(t.getTenBA());
						tf_status.setText(t.getTRANGTHAI());
						tf_numofseat.setText(String.valueOf(t.getSOCHONGOI()));
					}
					else {
						JOptionPane.showMessageDialog(bt_tFind, "Không tìm thấy bàn ăn!!");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(bt_tFind, "Không tìm thấy bàn ăn!!");
					tf_tId.setText("");
					tf_tName.setText("");
					tf_status.setText("");
					tf_numofseat.setText("");
				} finally {
					tf_tFind.setText("Nhập mã bàn:");
				}
			}
		});
		bt_tFind.setIcon(new ImageIcon("/Users/lamthu/Downloads/Magnifier-icon.png"));
		bt_tFind.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_table.add(bt_tFind);
		
		JLabel lb_tImage = new JLabel();
		lb_tImage.setIcon(new ImageIcon(new ImageIcon("/Users/lamthu/Downloads/Picture2.png")
				.getImage().getScaledInstance(371, 190, Image.SCALE_DEFAULT)));
		lb_tImage.setBounds(6, 169, 371, 190);
		p_table.add(lb_tImage);
		
		//Panel Revenue
		
		JPanel p_revenue = new JPanel();
		p_revenue.setBackground(new Color(245, 175, 125));
		layeredPane.add(p_revenue, "name_321469410338814");
		p_revenue.setLayout(null);
		
		JTable tb_revenue = new JTable();
		model5 = new DefaultTableModel();
		model5.setColumnIdentifiers(new String[] {"Mã hoá đơn", "Tên khách hàng", "Mã bàn ăn", "Mã nhân viên", "Ngày", "Tổng tiền"} );
		tb_revenue.setModel(model5);
		
		JScrollPane scrollPane_revenue = new JScrollPane();
		scrollPane_revenue.setBounds(57, 43, 697, 281);
		p_revenue.add(scrollPane_revenue);
		scrollPane_revenue.setViewportView(tb_revenue);
		
		JLabel lb_rImage = new JLabel();
		lb_rImage.setIcon(new ImageIcon(new ImageIcon("/Users/lamthu/Downloads/background-menu-nha-hang_090706772.jpeg")
				.getImage().getScaledInstance(814, 366, Image.SCALE_DEFAULT)));
		lb_rImage.setBounds(0, 0, 814, 366);
		p_revenue.add(lb_rImage);

		//FreeChart
		p_chart = new JPanel();
		p_chart.setForeground(new Color(219, 74, 77));
		p_chart.setBackground(new Color(250, 203, 135));
		layeredPane.add(p_chart, "name_26109651612696");
		
		CategoryDataset dataset = LoadDataChart();
		
        chart = createChart(dataset);
        p_chart.setLayout(null);
        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(255, 255, 255));
        chartPanel.setForeground(new Color(0, 0, 0));
        chartPanel.setDisplayToolTips(true);
        chartPanel.setBounds(6, 6, 802, 354);
        chartPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        p_chart.add(chartPanel);
        
		//Button LayeredPane
        
		JPanel p_button = new JPanel();
		p_button.setBackground(new Color(240, 241, 242));
		frame.getContentPane().add(p_button, BorderLayout.NORTH);
	
		JButton bt_main = new JButton("Trang chủ");
		bt_main.setForeground(new Color(219, 74, 77));
		bt_main.setBackground(new Color(250, 203, 135));
		bt_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		bt_main.setBorderPainted(false);
		bt_main.setOpaque(true);
		bt_main.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_button.add(bt_main);
				
		JButton bt_staff = new JButton("Nhân viên");
		bt_staff.setForeground(new Color(219, 74, 77));
		bt_staff.setBackground(new Color(250, 203, 135));
		bt_staff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(p_staff);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		bt_staff.setBorderPainted(false);
		bt_staff.setOpaque(true);
		bt_staff.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_button.add(bt_staff);
				
		JButton bt_food = new JButton("Món ăn");
		bt_food.setForeground(new Color(219, 74, 77));
		bt_food.setBackground(new Color(250, 203, 135));
		bt_food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(p_food);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		bt_food.setBorderPainted(false);
		bt_food.setOpaque(true);
		bt_food.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_button.add(bt_food);
				
		JButton bt_revenue = new JButton("Doanh thu");
		bt_revenue.setForeground(new Color(219, 74, 77));
		bt_revenue.setBackground(new Color(250, 203, 135));
		bt_revenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(p_revenue);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		JButton bt_table = new JButton("Bàn ăn");
		bt_table.setBackground(new Color(250, 203, 135));
		bt_table.setForeground(new Color(219, 74, 77));
		bt_table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(p_table);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		bt_table.setBorderPainted(false);
		bt_table.setOpaque(true);
		bt_table.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_button.add(bt_table);
		bt_revenue.setBorderPainted(false);
		bt_revenue.setOpaque(true);
	    bt_revenue.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_button.add(bt_revenue);
		
		JButton bt_chart = new JButton("Thống kê");
		bt_chart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(p_chart);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		bt_chart.setBackground(new Color(250, 203, 135));
		bt_chart.setForeground(new Color(219, 74, 77));
		bt_chart.setBorderPainted(false);
		bt_chart.setOpaque(true);
		bt_chart.setFont(new Font("Tahoma", Font.BOLD, 13));
		p_button.add(bt_chart);	
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
