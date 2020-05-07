import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private static DateTransform dateTransform;
    public static void main(String[] args) {
        // 创建JFrame实例
        JFrame frame = new JFrame("Time-Transform");
        frame.setSize(700, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建Panel
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        // 添加组件到面板
        placeComponents(panel);

        setJframeLocation(frame);
        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // 创建JLabel
        JLabel timestampLabel = new JLabel("时间戳：");
        timestampLabel.setBounds(10, 20, 80, 25);
        panel.add(timestampLabel);

        // 创建文本域用于用户输入
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // 下拉选择秒/毫秒
        JComboBox<Object> cmb = new JComboBox<>();
        cmb.addItem("秒(s)");
        cmb.addItem("毫秒(ms)");
        cmb.setBounds(300, 20, 80, 25);
        panel.add(cmb);

        // 创建转换button
        JButton firstTransformToTimestampButton = new JButton();
        firstTransformToTimestampButton.setText("转换");
        firstTransformToTimestampButton.setBounds(400, 20, 60, 25);
        panel.add(firstTransformToTimestampButton);

        // 输出的时间文本域，不可写
        JTextField outputText = new JTextField(20);
        outputText.setBounds(500,20,165,25);
        panel.add(outputText);

        firstTransformToTimestampButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timestamp = userText.getText();
                Integer index = cmb.getSelectedIndex();
                String result = "";
                if (index.equals(0)) {
                    result = dateTransform.timestampToString(timestamp);
                }else if (index.equals(1)) {
                    result = dateTransform.milTimestampToString(timestamp);
                }
                outputText.setText(result);
            }
        });

        // 创建JLabel
        JLabel stringTime = new JLabel("时间：");
        stringTime.setBounds(30, 100, 80, 25);
        panel.add(stringTime);

        // 创建文本域用于用户输入
        JTextField secondUserText = new JTextField(20);
        secondUserText.setBounds(120,100,165,25);
        panel.add(secondUserText);

        // 创建转换button
        JButton transformToTimestampButton = new JButton();
        transformToTimestampButton.setText("转换");
        transformToTimestampButton.setBounds(300, 100, 60, 25);
        panel.add(transformToTimestampButton);

        // 输出的时间文本域，不可写
        JTextField secondOutputText = new JTextField(20);
        secondOutputText.setBounds(400,100,165,25);
        panel.add(secondOutputText);

        // 下拉选择秒/毫秒
        JComboBox<Object> secondCmb = new JComboBox<Object>();
        secondCmb.addItem("秒(s)");
        secondCmb.addItem("毫秒(ms)");
        secondCmb.setBounds(600, 100, 80, 25);
        panel.add(secondCmb);

        transformToTimestampButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = secondUserText.getText();
                Integer index = secondCmb.getSelectedIndex();
                String result = "";
                if (index.equals(0)) {
                    result = dateTransform.stringToTimestamp(string);
                }else if (index.equals(1)) {
                    result = dateTransform.stringToMilTimestamp(string);
                }
                secondOutputText.setText(result);
            }
        });

        // 创建JLabel
        JLabel currentTimeJlabel = new JLabel("当前时间戳：");
        currentTimeJlabel.setBounds(30, 200, 150, 50);
        currentTimeJlabel.setFont(new Font("Serif", Font.BOLD, 22));
        panel.add(currentTimeJlabel);

        // 创建JLabel
        JLabel currentTimestampJlabel = new JLabel();
        currentTimestampJlabel.setBounds(200, 200, 150, 50);
        currentTimestampJlabel.setFont(new Font("Serif", Font.BOLD, 22));
        panel.add(currentTimestampJlabel);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long currentTimeStamp = System.currentTimeMillis() / 1000;
                    currentTimestampJlabel.setText(String.valueOf(currentTimeStamp));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public static void setJframeLocation(JFrame frame) {
        int windowWidth = frame.getWidth(); //获得窗口宽
        int windowHeight = frame.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
    }
}
