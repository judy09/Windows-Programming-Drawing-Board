import javax.swing.SwingUtilities;

import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter; 
import java.util.Random;
import javax.swing.ImageIcon;

import javax.swing.JButton;


public class SwingPaintDemo3 extends JPanel{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SwingPaintDemo3 Draw=new SwingPaintDemo3();
        Draw.InitUI();
	}
	
	private int squareX = 458;
	private int squareY = 10;
	private int squareX2 = 498;
	private int squareY2 = 10;
	private int squareW = 20;
	private int squareH = 20;
	JLabel jLabel = null;
	JLabel jLabel2 = null;
	int aa=0;
	int bb=0;
	int cc=0;
	// DrawListener a = new DrawListener();
	
	public SwingPaintDemo3(){
		jLabel = new JLabel ("a");
		jLabel.setIcon(new ImageIcon("p5.png"));
        add(jLabel);
		
        jLabel2 = new JLabel ("b");
		jLabel2.setIcon(new ImageIcon("p6.png"));
        add(jLabel2);
        setBorder(BorderFactory.createLineBorder(Color.black));

		 addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
				if((e.getX() >= squareX-squareW && e.getX() <= squareX+squareW)&& (e.getY() >= squareY-squareH && e.getY() <= squareY+squareH)){
					moveSquare(e.getX(),e.getY());
					jLabel.setLocation(e.getX()-jLabel.getWidth()/2,e.getY()-jLabel.getHeight()/2);
				}
				else if((e.getX() >= squareX2-squareW && e.getX() <= squareX2+squareW)&& (e.getY() >= squareY2-squareH && e.getY() <= squareY2+squareH)){
					moveSquare2(e.getX(),e.getY());
					jLabel2.setLocation(e.getX()-jLabel2.getWidth()/2,e.getY()-jLabel2.getHeight()/2);
				}
			}
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
				if((e.getX() >= squareX-squareW && e.getX() <= squareX+squareW)&& (e.getY() >= squareY-squareH && e.getY() <= squareY+squareH)){
					moveSquare(e.getX(),e.getY());
					jLabel.setLocation(e.getX()-jLabel.getWidth()/2,e.getY()-jLabel.getHeight()/2);
				}
				else if((e.getX() >= squareX2-squareW && e.getX() <= squareX2+squareW)&& (e.getY() >= squareY2-squareH && e.getY() <= squareY2+squareH)){
					moveSquare2(e.getX(),e.getY());
					jLabel2.setLocation(e.getX()-jLabel2.getWidth()/2,e.getY()-jLabel2.getHeight()/2);
				}
				
				if(squareX == squareX2 && squareY == squareY2) 
				{
					bb+=1;
					cc=1;
					setBackground(new Color(255,255,85));
					//setBackground(Color.YELLOW);
				}
				if(squareX != squareX2 && squareY != squareY2 && bb>0) 
				{
					cc=2;
					setBackground(new Color(229,229,229));
				}
            }
        });
	
	}
	
	private void moveSquare(int x, int y) {
        int OFFSET = 1;
        int tempX=squareX;
        int tempY=squareY;
        squareX=x;
        squareY=y;
            repaint(tempX,tempY,squareW+OFFSET,squareH+OFFSET);
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
 
    }
    

    private void moveSquare2(int x, int y) {
        int OFFSET = 1;
        int tempX2=squareX2;
        int tempY2=squareY2;
        squareX2=x;
        squareY2=y;
            repaint(tempX2,tempY2,squareW+OFFSET,squareH+OFFSET);
            repaint(squareX2,squareY2,squareW+OFFSET,squareH+OFFSET);
 
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
	
    public void InitUI(){
		
    	JFrame jf=new JFrame();
    	jf.setSize(1000,780);
    	jf.setTitle("簡單畫板");
    	jf.setDefaultCloseOperation(3);
    	jf.setLocationRelativeTo(null);
    	jf.setLayout(new BorderLayout());
    	
    	//例項化事件監聽類
    	DrawListener dl=new DrawListener(this);
    	
        //實現中間面板
    	this.setBackground(Color.WHITE);
    	jf.add(this,BorderLayout.CENTER);
    	
		 
    	//實現上面面板
    	JPanel ShapePanel=new JPanel();
    	// ShapePanel.setBackground(Color.black);
    	ShapePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	ShapePanel.setBackground(new Color(255,85,85));
    	String [] Shape={"直線","曲線","圓","噴槍","橡皮擦","矩形","橢圓","圓角矩形","弧線","多邊形","三角形","立體圓"};
    	for(int i=0;i<Shape.length;i++){
    		JButton button=new JButton(Shape[i]);
    		// button.setBackground(Color.WHITE);
    		button.addActionListener(dl);    //新增事件監聽機制
    		ShapePanel.add(button);
    	}
    	jf.add(ShapePanel,BorderLayout.NORTH);
    			
    	//實現顏色面板
    	JPanel ColorPanel=new JPanel();
    	ColorPanel.setBackground(Color.black);
    	ColorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	ColorPanel.setBackground(new Color(255,85,85));
    	Color [] color={Color.BLACK,Color.blue,Color.white,Color.gray,Color.red,Color.CYAN,Color.green,Color.darkGray,Color.pink};
    	for(int i=0;i<color.length;i++){
    		JButton button=new JButton();
    		button.addActionListener(dl);   //新增事件監聽機制
    		button.setPreferredSize(new Dimension(30,30));
    		button.setBackground(color[i]);
    		ColorPanel.add(button);
    	}
    	jf.add(ColorPanel,BorderLayout.SOUTH);
    	// jf.add(new MyPanel());
    	
    	jf.setVisible(true);
    	this.addMouseListener(dl);
		this.addMouseMotionListener(dl);
    }
	
	protected void paintComponent(Graphics g) {
        aa= aa+1;
        super.paintComponent(g);   	
        g.drawString("This is my custom Panel!"+aa ,10,20);
        // g.setColor(Color.RED);
        // g.fillRect(squareX,squareY,squareW,squareH);
        // g.setColor(Color.BLACK);
        // g.drawRect(squareX,squareY,squareW,squareH);
		// g.setColor(Color.cyan);
        // g.fillRect(squareX2,squareY2,squareW,squareH);
        // g.setColor(Color.BLACK);
        // g.drawRect(squareX2,squareY2,squareW,squareH);
    }  
}

class DrawListener extends MouseAdapter implements ActionListener{
	private int x1, y1, x2, y2;
	private int newx1,newy1,newx2,newy2;
	private Graphics2D g;
	private SwingPaintDemo3 df;
	private boolean flag=false;
	String shape="橡皮擦";
	Color color;
	private int [] arrx=new int[4];
	private int [] arry=new int[4];
	private  int temp=0;
		
	DrawListener(SwingPaintDemo3 d){
		df=d;
	}
	//獲取形狀和顏色
	public void actionPerformed(ActionEvent e){
	    if(e.getActionCommand().equals("")){
	    	JButton button = (JButton) e.getSource();  
			color = button.getBackground();   
			System.out.println("color = " + color);
	    }else{
	    	JButton button = (JButton) e.getSource();  
			shape = button.getActionCommand();   
			System.out.println("String = " + shape);
	    }
	}

	//實現畫筆
	 public void mousePressed(MouseEvent e) {
		 g=(Graphics2D) df.getGraphics();
		 g.setColor(color);
		 x1=e.getX();
		 y1=e.getY();
		
	 }
	
	 public void mouseReleased(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			if (shape.equals("直線")) {
				g.drawLine(x1, y1, x2, y2);
			}else if(shape.equals("弧線")){
				 g.drawArc(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1), 0, 180);
			}else if(shape.equals("多邊形")&&!flag){
				g.drawLine(x1, y1, x2, y2);
				newx1=x1;
				newy1=y1;
				newx2=x2;
				newy2=y2;
				flag=true;
			}else  if(shape.equals("圓")){
				 g.drawOval(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1));
			 }else if(shape.equals("矩形")){
				 g.drawRect(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1));
			 }else if(shape.equals("圓角矩形")){
				 g.drawRoundRect(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1),2,10);
			 }else if(shape.equals("橢圓")){
				 g.drawOval(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1));
			 }
	 }
 
	 public void mouseClicked(MouseEvent e) {
		 if(shape.equals("多邊形")&&flag){
			 x2=e.getX();
			 y2=e.getY();
			 if(e.getClickCount()==2){
				 g.drawLine(newx1, newy1, newx2, newy2);
				 flag=false;
			 }
			 g.drawLine(newx2, newy2, x2, y2);
			 
			 newx2=x2;
			 newy2=y2;
		   }else if(shape.equals("立體圓")){
//			   double a=-2,b=-2,c=-1.2,d=2;
			   double a=1.40,b=1.56,c=1.40,d=-6.56;
			   double x = 0,xo=0;
			   double y = 0,yo=0;
			   Color [] Col={Color.BLUE,Color.cyan,Color.green,Color.magenta,Color.red,Color.yellow};
			   for(int i=0;i<=90000;i++){
				  Random r=new Random();      //增加顏色
				  int R=r.nextInt(Col.length);
				  g.setColor(Col[R]);
				  
//				  x=Math.sin(a*yo)-Math.cos(b*xo);
//				  y=Math.sin(c*xo)-Math.cos(d*yo);
				  
				  x=d*Math.sin(a*xo)-Math.sin(b*yo);
				  y=c*Math.cos(a*xo)+Math.cos(b*yo);
				  int temp_x=(int)(x*50);
				  int temp_y=(int)(y*50);
				  
				  g.drawLine(temp_x+500, temp_y+300, temp_x+500, temp_y+300);
				  xo=x;
				  yo=y;
			}  
		}else if(shape.equals("三角形")){
			double a=-2,b=-2,c=-1.2,d=2;
			double x = 0,xo=0;
			double y = 0,yo=0;
			Color [] Col={Color.BLUE,Color.cyan,Color.green,Color.magenta,Color.red,Color.yellow};
			for(int i=0;i<=90000;i++){
				Random r=new Random();      //增加顏色
				int R=r.nextInt(Col.length);
				g.setColor(Col[R]);  
    			x=Math.sin(a*yo)-Math.cos(b*xo);
				y=Math.sin(c*xo)-Math.cos(d*yo);
				int temp_x=(int)(x*50);
				int temp_y=(int)(y*50);			  
				g.drawLine(temp_x+500, temp_y+300, temp_x+500, temp_y+300);
				xo=x;
				yo=y;
			}
		}
	}
	 
	
	 public void mouseDragged(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			if (shape.equals("曲線")) {
//				g.setStroke(new BasicStroke(10));			
//				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}else if(shape.equals("橡皮擦")){
				g.setStroke(new BasicStroke(80));							
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// if(cc=0)
					// g.setColor(Color.white);
				// if(cc=1)
					// g.setColor(Color.yellow);
				// if(cc=2)
					// g.setColor(Color.LIGHT_GRAY);
				// g.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}else if(shape.equals("噴槍")){
			//	g.setStroke(new BasicStroke(2));	  //不用加粗						
			//	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				for(int k=0;k<20;k++){
					Random i=new Random();       
					int a=i.nextInt(8);
					int b=i.nextInt(10);
					g.drawLine(x2+a, y2+b, x2+a, y2+b);
				}
			}
		}
}
