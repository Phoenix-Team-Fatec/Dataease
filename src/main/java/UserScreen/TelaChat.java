package UserScreen;

import LangChain.LmConnection;
import SQLConnection.InsertDB;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.ActionListener;
public class TelaChat extends javax.swing.JFrame implements ActionListener {


    JLabel caixa_resposta;
    JTextField caixa_entrada;
    private JPanel painel_chat = new JPanel();

    JButton botao_enviar;
    private JComboBox db_users = new JComboBox<>();

    public TelaChat(){
        ScreenComponents();
    }

    private void ScreenComponents(){
        //Configurações Básicas
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DataEase");
        setVisible(true);
        setSize(850,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44, 10, 75));

        //Configurações dos painéis;
        //Painel Chat
        painel_chat.setSize(850, 600);
        painel_chat.setVisible(true);
        painel_chat.setLayout(null);
        painel_chat.setBackground(new Color(44, 10, 75));
        add(painel_chat);

        //Caixa Resposta
        caixa_resposta = new JLabel();
        caixa_resposta.setForeground(new Color(255, 255,255));
        caixa_resposta.setBounds(37, 65, 776, 411); // Define a posição (x, y) e o tamanho (largura, altura)
        caixa_resposta.setBorder(new LineBorder(new Color(224, 170, 255)));
        caixa_resposta.setVisible(true);

        //Caixa Entrada
        caixa_entrada = new JTextField();
        caixa_entrada.setBounds(37, 493, 685, 36); // Define a posição (x, y) e o tamanho (largura, altura)
        caixa_entrada.setBackground(new Color(60, 9, 108));
        caixa_entrada.setForeground(Color.white);
        caixa_entrada.setBorder(new LineBorder(new Color(224, 170, 255)));
        caixa_entrada.setVisible(true);

        //Botao_enviar_prompt
        botao_enviar = new JButton("OK");
        botao_enviar.setBounds(737, 495, 76, 32); // Define a posição (x, y) e o tamanho (largura, altura)
        botao_enviar.setBorder(new LineBorder(new Color(224, 170, 255)));
        botao_enviar.setBackground(new Color(157,78,221));
        botao_enviar.addActionListener(this::sendText);

        painel_chat.add(caixa_resposta);
        painel_chat.add(caixa_entrada);
        painel_chat.add(botao_enviar);

    }



    private void sendText(ActionEvent actionEvent) {
        String entrada = caixa_entrada.getText();
        LmConnection prompt = new LmConnection();
        prompt.setContent(entrada);

        // caixa_resposta.setText(prompt.getPrompt());

        InsertDB consulta = new InsertDB();
        consulta.setSql_prompt(prompt.getPrompt());

        List<String> resultados = consulta.select();

        // Concatenando os resultados em uma única String
        String resultadoFormatado = consulta.resultadoConcatenado(resultados);

        // Definindo a String concatenada como texto do Label
        caixa_resposta.setText(resultadoFormatado);
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(TelaChat::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}