package cafe;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class cafe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cafe frame = new cafe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//-------
	
	static Connection conn=null;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt1=null;
	static PreparedStatement pstmt2=null;
	static ResultSet rs=null;
	
	public static String driver="oracle.jdbc.driver.OracleDriver";
	public static String url="jdbc:oracle:thin:@100.100.0.3:1521:xe";
	public static String user="scott";
	public static String password="tiger";
	
	public static void accdb() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
		} catch(Exception dbcerr) {
			System.out.println("DB 연결 실패 : "+dbcerr);
		}
	}
		public static DecimalFormat df= new DecimalFormat("#.##");
		public String formatD(double number) {
			return df.format(number);
			
		}
		
	static int totalcost=0;
	
	static String jeryocolNames[]= {"원두","카페시럽","사이다","레몬","자몽","유자","우유","망고","딸기","블루베리"};
	static String ordercolNames[]= {"회원번호","메뉴","사이즈","수량","메뉴금액","할인금액","추가금액","충전금사용액","금액"};
	static String acccolNames[]= {"성명","회원번호","충전금액","쿠폰"};
	static DefaultTableModel jeryomodel=new DefaultTableModel(jeryocolNames,0);
	static DefaultTableModel costmodel=new DefaultTableModel(jeryocolNames,0);
	
	public static void jego() {
		JFrame jego=new JFrame("재고관리");
		jego.setBounds(100, 100, 1920, 1080);
		jego.setVisible(true);
		jego.getContentPane().setLayout(null);
		

		
		JLabel title=new JLabel("재고현황");
		title.setBounds(20, 10, 70, 30);
		jego.getContentPane().add(title);
		JTable jt=new JTable(jeryomodel);
		JScrollPane jeryosp=new JScrollPane(jt);
		jeryosp.setBounds(20, 50, 1700, 300);
		jego.getContentPane().add(jeryosp);
		
		JTable jct=new JTable(costmodel);
		JScrollPane jc=new JScrollPane(jct);
		jc.setBounds(1200, 400, 600, 400);
		jego.getContentPane().add(jc);
		String query="select * from jego";
		try {
			accdb();
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			costmodel.setNumRows(0);
			jeryomodel.setNumRows(0);
			while(rs.next()) {
				jeryomodel.addRow(new Object[] {rs.getInt("wondoo"),rs.getInt("cafesirup"),rs.getInt("cider"),rs.getInt("lemon"),rs.getInt("jamong"),rs.getInt("uja"),rs.getInt("milk"),rs.getInt("mango"),rs.getInt("strowbarry"),rs.getInt("bluebarry")});
				costmodel.addRow(new  Object[] {rs.getInt("wondoocost"),rs.getInt("cafesirupcost"),rs.getInt("cidercost"),rs.getInt("lemoncost"),rs.getInt("jamongcost"),rs.getInt("ujacost"),rs.getInt("milkcost"),rs.getInt("mangocost"),rs.getInt("strowbarrycost"),rs.getInt("bluebarrycost")});
			
			}
		}catch(Exception tverr) {
			System.out.println("데이터 불러오기 오류 : "+tverr);
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception tvcerr) {
				System.out.println("데이터 불러오기 닫기 오류 : "+tvcerr);
			}
		}
		
		
		JLabel jumuntitle=new JLabel("재료주문");
		jumuntitle.setBounds(20, 400, 70, 30);
		jego.getContentPane().add(jumuntitle);
				
		JButton wondoobtn=new JButton("원두");
		wondoobtn.setBounds(20, 440, 90, 30);
		jego.getContentPane().add(wondoobtn);
		
		JButton cafesirupbtn=new JButton("카페시럽");
		cafesirupbtn.setBounds(120, 440, 90, 30);
		jego.getContentPane().add(cafesirupbtn);
		
		JButton ciderbtn=new JButton("사이다");
		ciderbtn.setBounds(220, 440, 90, 30);
		jego.getContentPane().add(ciderbtn);
		
		JButton lemonbtn=new JButton("레몬");
		lemonbtn.setBounds(320, 440, 90, 30);
		jego.getContentPane().add(lemonbtn);
		
		JButton jamongbtn=new JButton("자몽");
		jamongbtn.setBounds(420, 440, 90, 30);
		jego.getContentPane().add(jamongbtn);
		
		JButton ujabtn=new JButton("유자");
		ujabtn.setBounds(520, 440, 90, 30);
		jego.getContentPane().add(ujabtn);
		
		JButton milkbtn=new JButton("우유");
		milkbtn.setBounds(620, 440, 90, 30);
		jego.getContentPane().add(milkbtn);
		
		JButton mangobtn=new JButton("망고");
		mangobtn.setBounds(720, 440, 90, 30);
		jego.getContentPane().add(mangobtn);
		
		JButton strowbarrybtn=new JButton("딸기");
		strowbarrybtn.setBounds(820, 440, 90, 30);
		jego.getContentPane().add(strowbarrybtn);	
		
		JButton bluebarrybtn=new JButton("블루베리");
		bluebarrybtn.setBounds(920, 440, 90, 30);
		jego.getContentPane().add(bluebarrybtn);

		
		JTextField jegosu=new JTextField();
		jegosu.setBounds(640, 590, 100, 30);
		jego.getContentPane().add(jegosu);
		JTextField jeryo=new JTextField();
		jeryo.setBounds(340, 590, 300, 30);
		jego.getContentPane().add(jeryo);
		JTextField jegocost=new JTextField();
		jegocost.setBounds(740, 590, 80, 30);
		jego.getContentPane().add(jegocost);
		JLabel su=new JLabel("수량");
		su.setBounds(670, 550, 100, 30);
		jego.getContentPane().add(su);
		JLabel cost=new JLabel("개당가격");
		cost.setBounds(750, 550, 100, 30);
		jego.getContentPane().add(cost);
		JButton ok=new JButton("결정");
		ok.setBounds(840, 590, 100, 30);
		jego.getContentPane().add(ok);
		
		JButton order=new JButton("주문");
		order.setBounds(960, 590, 100, 30);
		jego.getContentPane().add(order);
		jeryo.setEditable(false);
		
		
		wondoobtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jeryo.setText("원두를 주문하실건가요? 수량을 입력하세요.");
			}
			
		});
		cafesirupbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("카페시럽을 주문하실건가요? 수량을 입력하세요.");
			}
			
		});
		 ciderbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("사이다를 주문하실건가요? 수량을 입력하세요.");
			}
			 
		 });
		 lemonbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("레몬을 주문하실건가요? 수량을 입력하세요.");
			}
			 
		 });
		 jamongbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("자몽을 주문하실건가요? 수량을 입력하세요.");
			}
			 
		 });
		 ujabtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("유자를 주문하실건가요? 수량을 입력하세요.");
			}
			 
		 });
		 milkbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("우유를 주문하실건가요? 수량을 입력하세요.");
			}
			 
		 });
		 mangobtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("망고를 주문하실건가요? 수량을 입력하세요.");
			}
			 
		 });
		 strowbarrybtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("딸기를 주문하실건가요? 수량을 입력하세요.");
			}
			 
		 });
		 bluebarrybtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeryo.setText("블루베리를 주문하실건가요? 수량을 입력하세요.");
			}
			 
		 });		
		 ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text=jeryo.getText();
				int su=0;
				int jeryocost=0;
				if(text.contains("원두")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 0);
					jeryomodel.setValueAt(su, 0, 0);
					jegocost.setText(Integer.toString(jeryocost*su));
					costmodel.setValueAt(jeryocost*su, 0, 0);
					jegosu.setText(null);
					jegocost.setText(null);
				}
				if(text.contains("카페시럽")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 1);
					jeryomodel.setValueAt(su, 0, 1);
					jegocost.setText(Integer.toString(jeryocost*su));
					costmodel.setValueAt(jeryocost*su, 0, 1);
					jegosu.setText(null);
					jegocost.setText(null);
				}
				if(text.contains("사이다")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 2);
					jeryomodel.setValueAt(su, 0, 2);
					costmodel.setValueAt(jeryocost*su, 0, 2);
					jegocost.setText(Integer.toString(jeryocost*su));
					jegosu.setText(null);
					jegocost.setText(null);

				}
				if(text.contains("레몬")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 3);
					jeryomodel.setValueAt(su, 0, 3);
					costmodel.setValueAt(jeryocost*su, 0, 3);
					jegocost.setText(Integer.toString(jeryocost*su));
					jegosu.setText(null);
					jegocost.setText(null);
					
				}
				if(text.contains("자몽")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 4);
					jeryomodel.setValueAt(su, 0, 4);
					costmodel.setValueAt(jeryocost*su, 0, 4); 
					jegocost.setText(Integer.toString(jeryocost*su));
					jegosu.setText(null);
					jegocost.setText(null);

				}
				if(text.contains("유자")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					 su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 5);
					 jeryomodel.setValueAt(su, 0, 5);
					 costmodel.setValueAt(jeryocost*su, 0, 5);
					 jegocost.setText(Integer.toString(jeryocost*su));
					jegosu.setText(null);
					jegocost.setText(null);

				}
				if(text.contains("우유")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 6);
					jeryomodel.setValueAt(su, 0, 6);
					costmodel.setValueAt(jeryocost*su, 0, 6);
					jegocost.setText(Integer.toString(jeryocost*su));
					jegosu.setText(null);
					jegocost.setText(null);

				}
				if(text.contains("망고")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 7);
					jeryomodel.setValueAt(su, 0, 7);
					costmodel.setValueAt(jeryocost*su, 0, 7);
					jegocost.setText(Integer.toString(jeryocost*su));
					jegosu.setText(null);
					jegocost.setText(null);

				}
				if(text.contains("딸기")) {
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 8);
					jeryomodel.setValueAt(su, 0, 8);
					costmodel.setValueAt(jeryocost*su, 0, 8);
					jegocost.setText(Integer.toString(jeryocost*su));
					jegosu.setText(null);
					jegocost.setText(null);

				}
				if(text.contains("블루베리")){
					jeryocost=Integer.parseInt(jegocost.getText());
					su=Integer.parseInt(jegosu.getText())+(int)jeryomodel.getValueAt(0, 9);
					jeryomodel.setValueAt(su, 0, 9);
					costmodel.setValueAt(jeryocost*su, 0, 9);
					jegocost.setText(Integer.toString(jeryocost*su));
					jegosu.setText(null);
					jegocost.setText(null);
				}
			}
		 });
		 
		 order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String ordersql="update jego set wondoo="+jeryomodel.getValueAt(0, 0)+", cafesirup="+jeryomodel.getValueAt(0, 0)+", cider="+jeryomodel.getValueAt(0, 2)+", lemon="+jeryomodel.getValueAt(0, 3)+",jamong="+jeryomodel.getValueAt(0, 4)+",uja="+jeryomodel.getValueAt(0, 5)+",milk="+jeryomodel.getValueAt(0, 6)+",mango="+jeryomodel.getValueAt(0, 7)+",strowbarry="+jeryomodel.getValueAt(0, 8)+",bluebarry="+jeryomodel.getValueAt(0, 9)+" where n="+"'1'";
					String costsql="update jego set wondoocost="+costmodel.getValueAt(0, 0)+", cafesirupcost="+costmodel.getValueAt(0, 0)+", cidercost="+costmodel.getValueAt(0, 2)+", lemoncost="+costmodel.getValueAt(0, 3)+",jamongcost="+costmodel.getValueAt(0, 4)+",ujacost="+costmodel.getValueAt(0, 5)+",milkcost="+costmodel.getValueAt(0, 6)+",mangocost="+costmodel.getValueAt(0, 7)+",strowbarrycost="+costmodel.getValueAt(0, 8)+",bluebarrycost="+costmodel.getValueAt(0, 9)+" where n="+"'1'";
					accdb();
					pstmt=conn.prepareStatement(ordersql);
					pstmt1=conn.prepareStatement(costsql);
					int cnt=pstmt.executeUpdate();
					int cnt1=pstmt1.executeUpdate();
					System.out.println("주문 성공");
				}catch(Exception orderdberr) {
					System.out.println("주문 DB 처리 오류 : "+orderdberr);
				}finally {
					try {
						pstmt.close();
						pstmt1.close();
						conn.close();
					}catch(Exception orderdbcerr) {
						System.out.println("주문 DB 처리 닫기 오류 : "+orderdbcerr);
					}
				}
			}
			 
		 });
	}
	
	public static void account() {
		JFrame acc=new JFrame("회원관리");
		acc.setBounds(100, 100, 1920, 1080);
		acc.setVisible(true);
		acc.setLayout(null);
		
		DefaultTableModel accmodel=new DefaultTableModel(acccolNames,0);
		
		String selectquery="select * from cafeuser";
		
		try {
			accdb();
			pstmt=conn.prepareStatement(selectquery);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				accmodel.addRow(new Object[] {rs.getString("username"),rs.getInt("userno"),rs.getInt("chargecost"),rs.getInt("havecoupon")});
			}
		}catch(Exception viewerr) {
			System.out.println("조회오류 : "+viewerr);
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception viewdberr) {
				System.out.println("조회닫기오류 : "+viewdberr);
			}
		}
		JLabel namelbl=new JLabel("성명");
		JLabel acnlbl=new JLabel("회원번호");
		JLabel chargecostlbl=new JLabel("충전금액");
		
		JTextField name=new JTextField();
		JTextField number=new JTextField();
		JTextField chargecost=new JTextField();
		
		namelbl.setBounds(5, 5, 100, 30);
		acnlbl.setBounds(5, 40, 100, 30);
		chargecostlbl.setBounds(5, 70, 100, 30);
		
		name.setBounds(110, 5, 100, 30);
		number.setBounds(110, 40, 100, 30);
		chargecost.setBounds(110, 70, 100, 30);
		
		JTable accv=new JTable(accmodel);
		JScrollPane acview=new JScrollPane(accv);
		acview.setBounds(1500, 5, 400, 600);
		acc.add(acview);
		
		JButton accinsert=new JButton("회원가입");
		JButton accupdate=new JButton("회원수졍");
		JButton accdelete=new JButton("회원삭제");
		
		accinsert.setBounds(300, 800, 100, 100);
		accupdate.setBounds(400, 800, 100, 100);
		accdelete.setBounds(500, 800, 100, 100);
		
		acc.add(accinsert);
		acc.add(accupdate);
		acc.add(accdelete);
		acc.add(namelbl);
		acc.add(acnlbl);
		acc.add(chargecostlbl);
		acc.add(name);
		acc.add(number);
		acc.add(chargecost);
		
		
		
		accv.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int row=accv.getSelectedRow();
				int col=accv.getSelectedColumn();
				name.setText((String)accv.getValueAt(row, col));
				number.setText(Integer.toString((int) accv.getValueAt(row, col+1)));
				chargecost.setText((Integer.toString((int)accv.getValueAt(row, col+2))));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		accinsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String insertquery="insert into cafeuser(username,userno,chargecost,havecoupon)"+"values(?,?,?,?)";
					accdb();
					
					accmodel.addRow(new Object[] {name.getText(),number.getText(),chargecost.getText(),0});
					
					pstmt=conn.prepareStatement(insertquery);
					pstmt.setString(1, name.getText());
					pstmt.setInt(2, Integer.parseInt(number.getText()));
					pstmt.setInt(3, Integer.parseInt(chargecost.getText()));
					pstmt.setInt(4, 0);
					
					int cnt=pstmt.executeUpdate();
					
					System.out.println("회원가입성공");
					name.setText(null);
					number.setText(null);
					chargecost.setText(null);
				}catch(Exception inserr) {
					System.out.println("회원가입오류 : "+inserr);
				} finally {
					try {
						pstmt.close();
						conn.close();
					}catch(Exception insdberr) {
						System.out.println("회원가입DB처리오류 : "+insdberr);
					}
				}
			}
			
		});
		accupdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String updatequery="update cafeuser set username='"+name.getText()+"', userno='"+number.getText()+"', chargecost='"+chargecost.getText()+"'where userno='"+number.getText()+"'";
					accdb();
					pstmt=conn.prepareStatement(updatequery);
					
					int cnt=pstmt.executeUpdate();
					System.out.println("회원수정완료");
					accmodel.setNumRows(0);
		try {
			accdb();
			pstmt=conn.prepareStatement(selectquery);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				accmodel.addRow(new Object[] {rs.getString("username"),rs.getInt("userno"),rs.getInt("chargecost"),rs.getInt("havecoupon")});
			}
		}catch(Exception viewerr) {
			System.out.println("조회오류 : "+viewerr);
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception viewdberr) {
				System.out.println("조회닫기오류 : "+viewdberr);
			}
		}
				}catch(Exception upderr) {
					System.out.println("회원수정오류 : "+upderr);
				} finally {
					try {
						pstmt.close();
						conn.close();
					}catch(Exception upddberr) {
						System.out.println("회원수정DB처리오류 : "+upddberr);
					}
				}
				
			}
			
		});	
		accdelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String delquery="delete from cafeuser where userno='"+number.getText()+"'";
					accdb();
					pstmt=conn.prepareStatement(delquery);
					int cnt=pstmt.executeUpdate();
					int row=accv.getSelectedRow();
					accmodel.removeRow(row);
					System.out.println("회원삭제완료");
					name.setText(null);
					number.setText(null);
					chargecost.setText(null);
				}catch(Exception delerr) {
					System.out.println("회원삭제오류 : "+delerr);
				} finally {
					try {
						pstmt.close();
						conn.close();
					}catch(Exception deldberr) {
						System.out.println("회원삭제DB처리오류 : "+deldberr);
					}
				}
			}
			
		});
}
		static double msu=0;
		static double amecost=2000;
		static double cafecost=3500;
		static double lemonadecost=3000;
		static double jamongadecost=4000;
		static double mangoadecost=3000;
		static double bluebarryadecost=3500;
		static double ujateacost=2000;
	public static void order() {
		JFrame order=new JFrame("주문");
		order.setBounds(100, 100, 1920, 1080);
		order.getContentPane().setLayout(null);
		order.setVisible(true);

		DefaultTableModel ordermodel=new DefaultTableModel(ordercolNames,0);
		JTable ot=new JTable(ordermodel);
		JScrollPane op=new JScrollPane(ot);
		op.setBounds(1200, 5, 700, 700);
		order.getContentPane().add(op);

		JLabel menulbl=new JLabel("메뉴");
		JLabel sizelbl=new JLabel("사이즈");
		JLabel sulbl=new JLabel("수량");
		JLabel clbl=new JLabel("금액");
		JLabel hm=new JLabel("충전금사용");
		JLabel paylbl=new JLabel("총액");
		
		menulbl.setBounds(100, 900, 100, 100);
		order.getContentPane().add(menulbl);
		sizelbl.setBounds(250, 900, 100, 100);
		order.getContentPane().add(sizelbl);
		sulbl.setBounds(530, 820, 100, 100);
		order.getContentPane().add(sulbl);
		clbl.setBounds(730, 820, 100, 100);
		order.getContentPane().add(clbl);
		hm.setBounds(1283, 900, 150, 100);
		order.getContentPane().add(hm);
		paylbl.setBounds(1510, 900, 100, 100);
		order.getContentPane().add(paylbl);
			try {
				String ovsql="select * from cafeorder";
				accdb();
				pstmt=conn.prepareStatement(ovsql);
				rs=pstmt.executeQuery();
			ordermodel.setNumRows(0);
				while(rs.next()) {
					ordermodel.addRow(new Object[] {rs.getInt("userno"),rs.getString("menu"),rs.getString("sizea"),rs.getInt("su"),rs.getInt("menupay"),rs.getInt("dc"),rs.getInt("addpay"),rs.getInt("chargemoney"),rs.getInt("pay")});
					}
			}catch(Exception overr) {
				System.out.println("주문내역조회오류 : "+overr);
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				}catch(Exception ovdberr) {
					System.out.println("주문내역조회 DB 처리 오류 : "+ovdberr);
				}
			}
		JButton amebtn=new JButton("아메리카노");
		amebtn.setBounds(5, 5, 150, 30);
		order.getContentPane().add(amebtn);
		JButton cafelattebtn=new JButton("카페라떼");
		cafelattebtn.setBounds(5, 35, 150, 30);
		order.getContentPane().add(cafelattebtn);
		JButton lemonadebtn=new JButton("레모네이드");
		lemonadebtn.setBounds(5, 65, 150, 30);
		order.getContentPane().add(lemonadebtn);
		JButton jamongadebtn=new JButton("자몽에이드");
		jamongadebtn.setBounds(5, 95, 150, 30);
		order.getContentPane().add(jamongadebtn);
		JButton mangoadebtn=new JButton("망고에이드");
		mangoadebtn.setBounds(5, 125, 150, 30);
		order.getContentPane().add(mangoadebtn);
		JButton bluebarryadebtn=new JButton("블루베리에이드");
		bluebarryadebtn.setBounds(5, 155, 150, 30);
		order.getContentPane().add(bluebarryadebtn);
		JButton ujateabtn=new JButton("유자차");
		ujateabtn.setBounds(5, 180, 150, 30);
		order.getContentPane().add(ujateabtn);
		JTextField menu=new JTextField();
		menu.setBounds(140, 900, 100, 100);
		order.getContentPane().add(menu);
	
		JTextField size=new JTextField();
		size.setBounds(300, 900, 100, 100);
		order.getContentPane().add(size);
		JTextField su=new JTextField(df.format(msu));
		su.setBounds(500, 900, 100, 100);
		order.getContentPane().add(su);
		JTextField cost=new JTextField();
		cost.setBounds(700, 900, 100, 100);
		order.getContentPane().add(cost);
		JTextField dc=new JTextField("0");
		dc.setBounds(900, 900, 100, 100);
		order.getContentPane().add(dc);
		JTextField addpay=new JTextField("0");
		addpay.setBounds(1100, 900, 100, 100);
		order.getContentPane().add(addpay);
		JLabel addpaylbl=new JLabel("추가금액");
		addpaylbl.setBounds(1035, 900, 100, 100);
		order.getContentPane().add(addpaylbl);
		JTextField chargecost=new JTextField("0");
		chargecost.setBounds(1350, 900, 100, 100);
		order.getContentPane().add(chargecost);
		JTextField pay=new JTextField();
		pay.setBounds(1550, 900, 100, 100);
		order.getContentPane().add(pay);
		JButton minussu=new JButton("-");
		minussu.setBounds(430, 930, 60, 50);
		order.getContentPane().add(minussu);
		JButton plussu=new JButton("+");
		plussu.setBounds(610, 930, 60, 50);
		order.getContentPane().add(plussu);
		JLabel dclbl=new JLabel("할인금액");
		dclbl.setBounds(840, 900, 100, 100);
		order.getContentPane().add(dclbl);
		JRadioButton s=new JRadioButton("S");
		JRadioButton m=new JRadioButton("M");
		JRadioButton l=new JRadioButton("L");
		
		ButtonGroup sizegroup=new ButtonGroup();
		sizegroup.add(s);
		sizegroup.add(m);
		sizegroup.add(l);
		
		s.setBounds(270, 850, 50, 30);
		m.setBounds(320, 850, 50, 30);
		l.setBounds(370 ,850, 50, 30);
		
		order.getContentPane().add(s);
		order.getContentPane().add(m);
		order.getContentPane().add(l);
		JLabel cpulbl=new JLabel("회원번호");
		cpulbl.setBounds(730, 730, 100, 70);
		order.getContentPane().add(cpulbl);
		
		JTextField number=new JTextField();
		number.setBounds(800, 730, 100, 70);
		order.getContentPane().add(number);
		JButton cpuchk=new JButton("충전금액조회");
		cpuchk.setBounds(900, 730, 150, 70);
		order.getContentPane().add(cpuchk);
		
		JLabel ccv=new JLabel("현재충전금");
		ccv.setBounds(820, 790, 100, 70);
		order.getContentPane().add(ccv);
		JTextField chargecostview=new JTextField();
		chargecostview.setBounds(900, 800, 100, 70);
		order.getContentPane().add(chargecostview);
		chargecostview.setEditable(false);
		
		amebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menu.setText("아메리카노");
			}
			
		});
		cafelattebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menu.setText("카페라떼");
			}
			
		});
		lemonadebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menu.setText("레모네이드");
			}
			
		});
		jamongadebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menu.setText("자몽에이드");
			}
			
		});
		mangoadebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				menu.setText("망고에이드");
			}
			
		});
		bluebarryadebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menu.setText("블루베리에이드");
			}
			
		});
		ujateabtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menu.setText("유자차");
			}
			
		});
		
		s.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				size.setText(null);
				cost.setText(null);
				size.setText("S");
			}
			
		});
		m.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				size.setText(null);
				cost.setText(null);
				size.setText("M");
			}
			
		});
		l.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				size.setText(null);
				cost.setText(null);
				size.setText("L");
			}
			
		});
		
		minussu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				msu=msu-1;
				if(menu.getText().contains("아메리카노")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					addpay.setText(null);
					cost.setText(df.format(amecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("아메리카노")&&size.getText().contains("M")) {
					cost.setText(null);
					dc.setText(null);
					addpay.setText(null);
					cost.setText(df.format(amecost*msu));
					su.setText(df.format(msu)); 
				}
				if(menu.getText().contains("아메리카노")&&size.getText().contains("L")) {
					cost.setText(null);
					dc.setText(null);
					addpay.setText(null);
					cost.setText(df.format(amecost*msu));
					addpay.setText(df.format((amecost*0.8)*msu));
					su.setText(df.format(msu));
				}
				
				if(menu.getText().contains("카페라떼")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(cafecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("카페라떼")&&size.getText().contains("M")) {
					dc.setText(null);
					su.setText(null);
					cost.setText(null);
					cost.setText(df.format(cafecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("카페라떼")&&size.getText().contains("L")) {
					cost.setText(null);
					dc.setText(null);
					addpay.setText(null);
					cost.setText(df.format(cafecost*msu));
					addpay.setText(df.format((cafecost*0.8)*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("레모네이드")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(lemonadecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("레모네이드")&&size.getText().contains("M")) {
					dc.setText(null);
					su.setText(null);
					cost.setText(null);
					cost.setText(df.format(lemonadecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("레모네이드")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(lemonadecost*msu));
					addpay.setText(df.format((lemonadecost*0.8)*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("자몽에이드")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(jamongadecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("자몽에이드")&&size.getText().contains("M")) {
					dc.setText(null);
					su.setText(null);
					cost.setText(null);
					cost.setText(df.format(jamongadecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("자몽에이드")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(jamongadecost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((jamongadecost*0.8)*msu));
				}
				if(menu.getText().contains("망고에이드")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(mangoadecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("망고에이드")&&size.getText().contains("M")) {
					dc.setText(null);
					su.setText(null);
					cost.setText(null);
					cost.setText(df.format(mangoadecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("망고에이드")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(mangoadecost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((mangoadecost*0.8)*msu));
				}
				if(menu.getText().contains("블루베리에이드")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(bluebarryadecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("블루베리에이드")&&size.getText().contains("M")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(bluebarryadecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("블루베리에이드")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(bluebarryadecost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((bluebarryadecost*0.8)*msu));
				}
				if(menu.getText().contains("유자차")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(ujateacost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("유자차")&&size.getText().contains("M")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(ujateacost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("유자차")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(ujateacost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((ujateacost*0.8)*msu));
				}
			}			
		});
		plussu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				msu=msu+1;
				if(menu.getText().contains("아메리카노")&&size.getText().contains("S")) {
					cost.setText(null);
					su.setText(null);
					dc.setText(null);
					cost.setText(df.format(amecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("아메리카노")&&size.getText().contains("M")) {
					cost.setText(null);
					su.setText(null);
					cost.setText(df.format(amecost*msu));
					su.setText(df.format(msu));
					
				}
				if(menu.getText().contains("아메리카노")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(amecost*msu));
					addpay.setText(df.format((amecost*0.8)*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("카페라떼")&&size.getText().contains("S")) {
					cost.setText(null);
					su.setText(null);
					cost.setText(df.format(cafecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("카페라떼")&&size.getText().contains("M")) {
					cost.setText(null);
					su.setText(null);
					cost.setText(df.format(cafecost*msu));
					su.setText(df.format(msu));
				} 
				if(menu.getText().contains("카페라떼")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(cafecost*msu));
					addpay.setText(df.format((cafecost*0.8)*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("레모네이드")&&size.getText().contains("S")) {
					cost.setText(null);
					su.setText(null);
					dc.setText(null);
					cost.setText(df.format(lemonadecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("레모네이드")&&size.getText().contains("M")) {
					cost.setText(null);
					su.setText(null);
					dc.setText(null);
					cost.setText(df.format(lemonadecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("레모네이드")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(lemonadecost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((lemonadecost*0.8)*msu));
				}
				if(menu.getText().contains("자몽에이드")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(jamongadecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("자몽에이드")&&size.getText().contains("M")) {
					dc.setText(null);
					su.setText(null);
					cost.setText(null);
					cost.setText(df.format(jamongadecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("자몽에이드")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(jamongadecost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((jamongadecost*0.8)*msu));
				}
				if(menu.getText().contains("망고에이드")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(mangoadecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("망고에이드")&&size.getText().contains("M")) {
					dc.setText(null);
					su.setText(null);
					cost.setText(null);
					cost.setText(df.format(mangoadecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("망고에이드")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(mangoadecost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((mangoadecost*0.8)*msu));
				}
				if(menu.getText().contains("블루베리에이드")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(bluebarryadecost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("블루베리에이드")&&size.getText().contains("M")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(bluebarryadecost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("블루베리에이드")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(bluebarryadecost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((bluebarryadecost*0.8)*msu));
				}
				if(menu.getText().contains("유자차")&&size.getText().contains("S")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(ujateacost*msu));
					su.setText(df.format(msu));
					dc.setText(df.format(1000*msu));
				}
				if(menu.getText().contains("유자차")&&size.getText().contains("M")) {
					cost.setText(null);
					dc.setText(null);
					su.setText(null);
					cost.setText(df.format(ujateacost*msu));
					su.setText(df.format(msu));
				}
				if(menu.getText().contains("유자차")&&size.getText().contains("L")) {
					cost.setText(null);
					su.setText(null);
					addpay.setText(null);
					cost.setText(df.format(ujateacost*msu));
					su.setText(df.format(msu));
					addpay.setText(df.format((ujateacost*0.8)*msu));
				}
			}
			
		});
		cpuchk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int num=Integer.parseInt(number.getText());
					int n=0;
					int chargecost=0;
					String searchquery="select userno,chargecost from cafeuser";
					accdb();
					pstmt=conn.prepareStatement(searchquery);
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						n=rs.getInt("userno");
						chargecost=rs.getInt("chargecost");
					}
					if(n==num){
						System.out.println("조회성공");
						chargecostview.setText(null);
						chargecostview.setText(Integer.toString(chargecost));
					}
				}catch(Exception chkerr) {
					System.out.println("회원검색 오류 : "+chkerr);
				}finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					}catch(Exception chkdberr) {
						System.out.println("회원검색DB처리오류 : "+chkdberr);
					}
				}
			}
			
		});


		int msu=0;
		int amecost=2000;
		int cafecost=3500;
		int lemonadecost=3000;
		int jamongadecost=4000;
		int mangoadecost=3000;
		int bluebarryadecost=3500;
		int ujateacost=2000;
		JButton orderbtn=new JButton("주문");
		orderbtn.setBounds(1680, 900, 100, 70);
		order.getContentPane().add(orderbtn);
		
		orderbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String text=menu.getText();
					int asu=Integer.parseInt(su.getText());
					int acost=Integer.parseInt(cost.getText());
					int adc=Integer.parseInt(dc.getText());
					int ach=Integer.parseInt(chargecost.getText());
					int add=Integer.parseInt(addpay.getText());
					
						String order="insert into cafeorder values(?,?,?,?,?,?,?,?,?)";
						String updcc="update cafeuser set chargecost=chargecost-"+ach+"where userno="+number.getText();
					accdb();
					if(text.contains("아메리카노")) {
						String wondoominus="update jego set wondoo=wondoo-"+su.getText();
						String wondoominuslarge="update jego set wondoo=wondoo-"+Integer.parseInt(su.getText())*2;
						accdb();
						pstmt1=conn.prepareStatement(order);
						pstmt2=conn.prepareStatement(updcc);
						if(size.getText().contains("S")) {
						pstmt=conn.prepareStatement(wondoominus);
							cost.setText(null);
							cost.setText(Integer.toString(amecost));
							pay.setText(Integer.toString((asu*acost)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
						if(size.getText().contains("M")) {
							pstmt=conn.prepareStatement(wondoominus);
							cost.setText(null);
							cost.setText(Integer.toString(amecost));
							pay.setText(Integer.toString((asu*acost)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
						if(size.getText().contains("L")) {
							pstmt=conn.prepareStatement(wondoominuslarge);
							cost.setText(null);
							cost.setText(Integer.toString(amecost));
							pay.setText(Integer.toString((asu*acost+add)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
					}
					if(text.contains("카페라떼")) {
						String cafeminus= "update jego set cafesirup=cafesirup-"+su.getText()+", milk=milk-"+su.getText();
						String cafeminuslarge="update jego set cafesirup=cafesirup-"+Integer.parseInt(su.getText())*2+", milk=milk-"+Integer.parseInt(su.getText())*2;
				
							pstmt1=conn.prepareStatement(order);
							pstmt2=conn.prepareStatement(updcc);
						if(size.getText().contains("S")) {
							pstmt=conn.prepareStatement(cafeminus);
							cost.setText(null);
							cost.setText(Integer.toString(cafecost));
							pay.setText(Integer.toString((asu*acost)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
						if(size.getText().contains("M")) {
							pstmt=conn.prepareStatement(cafeminus);
							cost.setText(null);
							cost.setText(Integer.toString(cafecost));
							pay.setText(Integer.toString((asu*acost)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
						if(size.getText().contains("L")) {
							pstmt=conn.prepareStatement(cafeminuslarge);
							cost.setText(null);
							cost.setText(Integer.toString(cafecost));
							pay.setText(Integer.toString((asu*acost+add)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
					}
					if(text.contains("레모네이드")) {
						String lemonademinus="update jego set lemon=lemon-"+Integer.parseInt(su.getText())*2+", cider=cider-"+su.getText();
						String lemonademinuslarge="update jego set lemon=lemon-"+Integer.parseInt(su.getText())*2+", cider=cider-"+su.getText();
						
						pstmt1=conn.prepareStatement(order);
						pstmt2=conn.prepareStatement(updcc);
						
						if(size.getText().contains("S")) {
							pstmt=conn.prepareStatement(lemonademinus);
							cost.setText(null);
							cost.setText(Integer.toString(lemonadecost));
							pay.setText(Integer.toString((asu*acost)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
						if(size.getText().contains("M")) {
							pstmt=conn.prepareStatement(lemonademinus);
							cost.setText(null);
							cost.setText(Integer.toString(lemonadecost));
							pay.setText(Integer.toString((asu*acost)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
						if(size.getText().contains("L")) {
							pstmt=conn.prepareStatement(lemonademinuslarge);
							cost.setText(null);
							cost.setText(Integer.toString(lemonadecost));
							pay.setText(Integer.toString((asu*acost+add)-adc-ach));
						}
					}
					if(text.contains("자몽에이드")) {
						String jamongademinus="update jego set jamong=jamong-"+Integer.parseInt(su.getText())*2+", cider=cider-"+su.getText();
						String jamongademinuslarge="update jego set jamong=jamong-"+Integer.parseInt(su.getText())*3+", cider=cider-"+su.getText();
						
						pstmt1=conn.prepareStatement(order);
						pstmt2=conn.prepareStatement(updcc);
						
						if(size.getText().contains("S")) {
							pstmt=conn.prepareStatement(jamongademinus);
							cost.setText(null);
							cost.setText(Integer.toString(jamongadecost));
							pay.setText(Integer.toString((asu*acost)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
						if(size.getText().contains("M")) {
							pstmt=conn.prepareStatement(jamongademinus);
							cost.setText(null);
							cost.setText(Integer.toString(jamongadecost));
							pay.setText(Integer.toString((asu*acost)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
						if(size.getText().contains("L")) {
							pstmt=conn.prepareStatement(jamongademinuslarge);
							cost.setText(null);
							cost.setText(Integer.toString(jamongadecost));
							pay.setText(Integer.toString((asu*acost+add)-adc-ach));
							chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
						}
					}
						if(text.contains("망고에이드")) {
							String mangoademinus="update jego set mango=mango-"+Integer.parseInt(su.getText())*2+", cider=cider-"+su.getText();
							String mangoademinuslarge="update jego set mango=mango-"+Integer.parseInt(su.getText())*3+", cider=cider-"+su.getText();
							
							pstmt1=conn.prepareStatement(order);
							pstmt2=conn.prepareStatement(updcc);
							
							if(size.getText().contains("S")) {
								pstmt=conn.prepareStatement(mangoademinus);
								cost.setText(null);
								cost.setText(Integer.toString(mangoadecost));
								pay.setText(Integer.toString((asu*acost)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
							if(size.getText().contains("M")) {
								pstmt=conn.prepareStatement(mangoademinus);
								cost.setText(null);
								cost.setText(Integer.toString(mangoadecost));
								pay.setText(Integer.toString((asu*acost)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
							if(size.getText().contains("L")) {
								pstmt=conn.prepareStatement(mangoademinuslarge);
								cost.setText(null);
								cost.setText(Integer.toString(mangoadecost));
								pay.setText(Integer.toString((asu*acost+add)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
						}
						if(text.contains("블루베리에이드")) {
							String bluebarryademinus="update jego set bluebarry=bluebarry-"+Integer.parseInt(su.getText())*2+", cider=cider-"+su.getText();
							String bluebarryademinuslarge="update jego set bluebarry=bluebarry-"+Integer.parseInt(su.getText())*3+", cider=cider-"+su.getText();
							
							pstmt1=conn.prepareStatement(order);
							pstmt2=conn.prepareStatement(updcc);
						
							if(size.getText().contains("S")) {
								pstmt=conn.prepareStatement(bluebarryademinus);
								cost.setText(null);
								cost.setText(Integer.toString(bluebarryadecost));
								pay.setText(Integer.toString((asu*acost)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
							if(size.getText().contains("M")) {
								pstmt=conn.prepareStatement(bluebarryademinus);
								cost.setText(null);
								cost.setText(Integer.toString(bluebarryadecost));
								pay.setText(Integer.toString((asu*acost)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
							if(size.getText().contains("L")) {
								pstmt=conn.prepareStatement(bluebarryademinuslarge);
								cost.setText(null);
								cost.setText(Integer.toString(bluebarryadecost));
								pay.setText(Integer.toString((asu*acost+add)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
						}
						if(text.contains("유자차")) {
							String ujateaminus="update jego set uja=uja-"+Integer.parseInt(su.getText());
							String ujateaminuslarge="update jego set uja=uja-"+Integer.parseInt(su.getText())*2;
							
							pstmt1=conn.prepareStatement(order);
							pstmt2=conn.prepareStatement(updcc);
							
							if(size.getText().contains("S")) {
								pstmt=conn.prepareStatement(ujateaminus);
								cost.setText(null);
								cost.setText(Integer.toString(ujateacost));
								pay.setText(Integer.toString((asu*acost)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
							if(size.getText().contains("M")) {
								pstmt=conn.prepareStatement(ujateaminus);
								cost.setText(null);
								cost.setText(Integer.toString(ujateacost));
								pay.setText(Integer.toString((asu*acost)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
							if(size.getText().contains("L")) {
								pstmt=conn.prepareStatement(ujateaminuslarge);
								cost.setText(null);
								cost.setText(Integer.toString(ujateacost));
								pay.setText(Integer.toString((asu*acost)-adc-ach));
								chargecostview.setText(Integer.toString(Integer.parseInt(chargecostview.getText())-ach));
							}
						}
					//DB 쿼리 대입
						pstmt1.setInt(1, Integer.parseInt(number.getText()));
						pstmt1.setString(2, menu.getText());
						pstmt1.setString(3, size.getText());
						pstmt1.setInt(4, Integer.parseInt(su.getText()));
						pstmt1.setInt(5, Integer.parseInt(cost.getText()));
						pstmt1.setInt(6, Integer.parseInt(dc.getText()));
						pstmt1.setInt(7, Integer.parseInt(addpay.getText()));
						pstmt1.setInt(8, Integer.parseInt(chargecost.getText()));
						pstmt1.setInt(9, Integer.parseInt(pay.getText()));
						
					ordermodel.addRow(new Object[] {number.getText(),menu.getText(),size.getText(),su.getText(),cost.getText(),dc.getText(),addpay.getText(),chargecost.getText(),pay.getText()});
					int cnt=pstmt.executeUpdate();
					int cnt1=pstmt1.executeUpdate();
					int cnt2=pstmt2.executeUpdate();
					
					System.out.println("주문성공");
					number.setText(null);
					menu.setText(null);
					size.setText(null);
					su.setText(null);
					cost.setText("0");
					dc.setText("0");
					addpay.setText("0");
					chargecost.setText("0");
					pay.setText(null);
				}catch(Exception ordererr) {
					System.out.println("주문 오류 : "+ordererr);
				}finally {
					try {
						pstmt.close();
						pstmt1.close();
						if(pstmt2!=null)pstmt2.close();
						conn.close();
					}catch(Exception orderdberr) {
						System.out.println("주문 DB처리 오류 : "+orderdberr);
					}
				}
			}
			
		});
}
		
	/**
	 * Create the frame.
	 */
	public cafe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setTitle("카페관리 프로그램");
		JButton jegoorder = new JButton("재고관리");
		jegoorder.setBounds(12, 10, 97, 23);
		contentPane.add(jegoorder);		
		JButton creuser = new JButton("회원관리");
		creuser.setBounds(12, 40, 97, 23);
		contentPane.add(creuser);
		
		
		JButton menuorder = new JButton("주문");
		menuorder.setBounds(12, 73, 97, 23);
		contentPane.add(menuorder);

		jegoorder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jego();
			}
			
		});
		menuorder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				order();
			}
			
		});
		creuser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				account();
			}
			
		});
	}
}