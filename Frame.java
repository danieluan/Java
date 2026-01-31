package Frames;

import Poo.Gamed.exception.FileOpenFailedException;
import po.gamed.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Frame extends JFrame implements ActionListener {

    JPanel panelIncialTop;
    JPanel panelIncialBottom;
    JButton Abrir, Guardar, MenudeGestaodeUtentes, MenudeGestãodeObras, MenudeGestãodeRequisições;
    JFrame frameAbrir, newFrame;
    ArrayList<String> arrayOptions;
    Mediateca mediateca = new Mediateca();

    Clip clip;
    File file;
    AudioInputStream audioStream;

    public Frame() {

        String arquivo = "C:\\Users\\PC\\OneDrive\\Desktop\\Samba.dat";
        try {
            mediateca = Mediateca.load(arquivo);
            JOptionPane.showMessageDialog(null, "Dados carregados com sucesso");
        } catch (FileOpenFailedException e) {
            mediateca = new Mediateca();

            // dados iniciais
            mediateca.addUtente(new Utente("Daniel", "e936640156@gmail.com"));
        }



        arrayOptions = new ArrayList<>();

        arrayOptions.add("Obras de referência");
        arrayOptions.add("Obras de ficção científica");
        arrayOptions.add("Obras de ciência e tecnologia");

        panelIncialTop = new JPanel();
        panelIncialBottom = new JPanel();
        Abrir = new JButton("Abrir");
        Guardar = new JButton("Guardar");
        MenudeGestaodeUtentes = new JButton("Menu de Gestão de Utentes");
        MenudeGestãodeObras = new JButton("Menu de Gestão de Obras");
        MenudeGestãodeRequisições = new JButton("Menu de Gestão de Requisições");

        panelIncialBottom.setLayout(null);
        panelIncialTop.setPreferredSize(new Dimension(500, 200));
        panelIncialBottom.setPreferredSize(new Dimension(500, 400));

        Abrir.setBounds(230, 30, 250, 40);
        Guardar.setBounds(230, 80, 250, 40);
        MenudeGestaodeUtentes.setBounds(230, 130, 250, 40);
        MenudeGestãodeObras.setBounds(230, 180, 250, 40);
        MenudeGestãodeRequisições.setBounds(230, 230, 250, 40);

        panelIncialBottom.setBackground(Color.white);
        panelIncialTop.setBackground(Color.white);

        Abrir.addActionListener(this);
        Guardar.addActionListener(this);
        MenudeGestaodeUtentes.addActionListener(this);
        MenudeGestãodeRequisições.addActionListener(this);
        MenudeGestãodeObras.addActionListener(this);

        this.setLayout(new BorderLayout(2, 4));
        this.add(panelIncialTop, BorderLayout.NORTH);
        this.add(panelIncialBottom, BorderLayout.SOUTH);

        panelIncialBottom.add(Abrir);
        panelIncialBottom.add(Guardar);
        panelIncialBottom.add(MenudeGestaodeUtentes);
        panelIncialBottom.add(MenudeGestãodeObras);
        panelIncialBottom.add(MenudeGestãodeRequisições);

        this.getContentPane().setBackground(Color.white);
        panelIncialTop.setLayout(new FlowLayout(FlowLayout.LEADING));

        this.setTitle("Seja Bem-vindo à Melhor Mediateca");

        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagens/logo.png"));
        Image im = ico.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iconFinal = new ImageIcon(im);
        JLabel logo = new JLabel(iconFinal);
        panelIncialTop.add(logo);

        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Abrir) {

        frameAbrir();

        }  else if (e.getSource() == MenudeGestaodeUtentes) {

            newFrame();

        } else if (e.getSource()==MenudeGestãodeObras) {

            frameAbrir();
        
        } else if (e.getSource()==MenudeGestãodeRequisições) {
            JFrame frame = new JFrame();
            JButton b1, b2;
            b1 = new JButton("Historico");
            b2 = new JButton("Requisitar");
frame.setLayout(null);
            b1.setBounds(42,10,150,25);
            b2.setBounds(192,10,150,25);

frame.add(b1);
frame.add(b2);
frame.setSize(400,100);
frame.setVisible(true);
frame.setLocationRelativeTo(null);
b1.addActionListener(e1 -> {
    /// ????????????????????????????????????????????????????Historico=================================================0
frame.dispose();
JFrame h = new JFrame();
h.setSize(500,500);


    JButton bntt = new JButton("Back");
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    JLabel label = new JLabel();

    label.setBounds(0,50,500,440);
    label.setBackground(new Color(200,100,100));
    panel.setBounds(0,40,500,440);
    panel.setBackground(Color.white);
    panel.add(label);

    h.add(panel);

    bntt.setBounds(350,10,120,30);
    h.add(bntt);
    bntt.addActionListener(e3 ->{
        h.dispose();

    });


h.setLocationRelativeTo(null);
h.setLayout(null);
h.setVisible(true);
});

b2.addActionListener(e1 -> {frameAbrir();
    frame.dispose();}
);

        }
         else if (e.getSource()==Guardar) {
             String file = "C:\\Users\\PC\\OneDrive\\Desktop\\Samba.dat";
            try {
                mediateca.save(file);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private JPanel card(Obra obra) {
        if (obra instanceof Livro) {
            JPanel panel = new JPanel();
            JButton ler, requisitar, devolver;
            panel.setPreferredSize(new Dimension(275, 200));

            JLabel url = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource(obra.getUrl()));
            Image img = icon.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH);
            url.setIcon(new ImageIcon(img));
            url.setBounds(15, 5, 120, 150);

            JLabel id = new JLabel("ID do Livro: " + obra.getId());
            id.setBounds(140, 0, 175, 12);
            id.setFont(new Font("Arial", Font.BOLD, 10));

            JLabel titulo = new JLabel("Titulo do Livro: " + obra.getTitulo());
            titulo.setBounds(140, 15, 175, 12);
            titulo.setFont(new Font("Arial", Font.BOLD, 10));

            JLabel categoria = new JLabel(obra.getCategoria());
            categoria.setFont(new Font("Arial", Font.ITALIC, 10));
            categoria.setBounds(140, 30, 175, 12);

            JLabel totalExemplares = new JLabel("Total de Livros: " + obra.getTotalExemplares());
            totalExemplares.setFont(new Font("Arial", Font.BOLD, 10));
            totalExemplares.setBounds(140, 45, 175, 12);

            JLabel totalDisponiveis = new JLabel("Disponiveis: " + obra.getExemplaresDisponiveis());
            totalDisponiveis.setFont(new Font("Arial", Font.BOLD, 10));
            totalDisponiveis.setBounds(140, 60, 175, 12);

            JLabel preco = new JLabel("Preço: " + obra.getPreco());
            preco.setFont(new Font("Arial", Font.BOLD, 10));
            preco.setBounds(140, 75, 175, 12);

            JLabel realizador = new JLabel("Autor: " + ((Livro) obra).getAutor());
            realizador.setFont(new Font("Arial", Font.BOLD, 10));
            realizador.setBounds(140, 90, 175, 12);

            JLabel dndac = new JLabel("ISBN: " + ((Livro) obra).getIsbn());
            dndac.setFont(new Font("Arial", Font.BOLD, 10));
            dndac.setBounds(140, 105, 175, 12);

            ler = new JButton("Ler");
            ler.setBounds(1, 165, 60, 25);

            ler.addActionListener(e -> {

                try {
                    File arquivo = new File("C:\\Users\\PC\\OneDrive\\Desktop\\book.pdf");

                    // Verifica se o Desktop é suportado
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(arquivo);
                    } else {
                        System.out.println("Seu sistema não suporta abrir arquivos automaticamente.");
                    }
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            });
            devolver = new JButton("Devolver");
            devolver.setBounds(163, 165, 100, 25);

            requisitar = new JButton("Requisitar");
            requisitar.setBounds(62, 165, 100, 25);

requisitar.addActionListener(e -> {

    JFrame req = new JFrame();
    JLabel label = new JLabel("Digite o seu seu ID ");
    label.setBounds(10,2, 200,20);
    req.setLayout(null);
    req.setLocationRelativeTo(null);
    JTextField field = new JTextField();
    JButton bntReq= new JButton("Requisitar");
    bntReq.addActionListener(e1 -> {
        try {
            mediateca.requisitarObra(Integer.parseInt(field.getText()),obra.getId());

req.dispose();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    });

    field.setBounds(2,50,150,25);
    bntReq.setBounds(152, 50,40,25);
    req.add(bntReq);
    req.add(label);
    req.add(field);
    req.setSize(350,150);
    req.setVisible(true);

});

devolver.addActionListener(e -> {
    JFrame req = new JFrame();
    JLabel label = new JLabel("Digite o seu ID para devolver a obra:  ");
    label.setBounds(10,2, 150,20);
    req.setLayout(null);
    req.setLocationRelativeTo(null);
    JTextField field = new JTextField();
    JButton bntReq= new JButton("Devolver");
    bntReq.addActionListener(e1 -> {
        try {
            mediateca.devolverObra(Integer.parseInt(field.getText()),obra.getId());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    });

    field.setBounds(2,50,150,25);
    bntReq.setBounds(152, 50,80,25);
    req.add(bntReq);
    req.add(label);
    req.add(field);
    req.setSize(350,150);
    req.setVisible(true);
});



            panel.setLayout(null);
            panel.add(ler);
            panel.add(devolver);
            panel.add(requisitar);
            panel.add(id);
            panel.add(titulo);
            panel.add(categoria);
            panel.add(totalExemplares);
            panel.add(totalDisponiveis);
            panel.add(preco);
            panel.add(dndac);
            panel.add(realizador);
            panel.add(url);

            return panel;
        } else {
            JPanel panel = new JPanel();
            JButton ouvir, requisitar, devolver;
            panel.setPreferredSize(new Dimension(275, 200));

            JLabel url = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource("/DVD/dvd.png"));
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            url.setIcon(new ImageIcon(img));
            url.setBounds(15, 5, 100, 100);

            JLabel id = new JLabel("ID do DVD: " + obra.getId());
            id.setBounds(120, 0, 175, 12);
            id.setFont(new Font("Arial", Font.BOLD, 10));

            JLabel titulo = new JLabel("Titulo do DVD: " + obra.getTitulo());
            titulo.setBounds(120, 15, 175, 12);
            titulo.setFont(new Font("Arial", Font.BOLD, 10));

            JLabel categoria = new JLabel(obra.getCategoria());
            categoria.setFont(new Font("Arial", Font.ITALIC, 10));
            categoria.setBounds(120, 30, 175, 12);

            JLabel totalExemplares = new JLabel("Total de DVDs: " + obra.getTotalExemplares());
            totalExemplares.setFont(new Font("Arial", Font.BOLD, 10));
            totalExemplares.setBounds(120, 45, 175, 12);

            JLabel totalDisponiveis = new JLabel("Disponiveis: " + obra.getExemplaresDisponiveis());
            totalDisponiveis.setFont(new Font("Arial", Font.BOLD, 10));
            totalDisponiveis.setBounds(120, 60, 175, 12);

            JLabel preco = new JLabel("Preço: " + obra.getPreco());
            preco.setFont(new Font("Arial", Font.BOLD, 10));
            preco.setBounds(120, 75, 175, 12);

            JLabel realizador = new JLabel("Realizador: " + ((DVD) obra).getRealizador());
            realizador.setFont(new Font("Arial", Font.BOLD, 10));
            realizador.setBounds(120, 90, 175, 12);

            JLabel dndac = new JLabel("Dndac: " + ((DVD) obra).getDndac());
            dndac.setFont(new Font("Arial", Font.BOLD, 10));
            dndac.setBounds(120, 105, 175, 12);

            ouvir = new JButton("Ouvir");
            ouvir.setBounds(1, 150, 60, 40);

            file = new File("C:\\Users\\PC\\OneDrive\\Desktop\\uWu Victory - Rod Kim.wav");
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {

                throw new RuntimeException(e);
            }
            ouvir.addActionListener(e -> {
                try {
                     audioStream = AudioSystem.getAudioInputStream(file);


                    clip.open(audioStream);


                 // toca



                    if (clip.isRunning()) {
                        clip.stop();
                        clip.close();// toca
                    } else {
                      // para
                        clip.start();

                    }


                } catch (Exception exception) {

                 JOptionPane.showMessageDialog(null, exception.getMessage());
                    clip.close();
                    clip.stop();
                }

            });
            requisitar = new JButton("Requisitar");
            requisitar.setBounds(62, 165, 100, 25);

            requisitar.addActionListener(e -> {
                JFrame req = new JFrame();
                JLabel label = new JLabel("Digite o seu ID: ");
                label.setBounds(1,2, 200,20);
                req.setLayout(null);
                req.setLocationRelativeTo(null);
                JTextField field = new JTextField();
                JButton bntReq= new JButton("Requisitar");

                bntReq.addActionListener(e1 -> {
                    try {
                        mediateca.requisitarObra(Integer.parseInt(field.getText()),obra.getId());
                        System.out.println(obra.getId());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                });


                field.setBounds(2,50,150,25);
                bntReq.setBounds(152, 50,50,25);
                req.add(bntReq);
                req.add(label);
                req.add(field);
                req.setSize(350,150);
                req.setVisible(true);
            });
            devolver = new JButton("Devolver");
            devolver.setBounds(163, 165, 100, 25);
devolver.addActionListener(e -> {
    JFrame f=new JFrame();
    JTextField R = new JTextField();
    JButton bnt = new JButton("Devolver");
    R.setBounds(10,20,200,25);
    bnt.setBounds(210,20,80,25);
    bnt.addActionListener(e1 -> {
        try {
            mediateca.devolverObra(Integer.parseInt(R.getText()),obra.getId());
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    });
    f.setSize(300,200);
    f.setLayout(null);
    f.add(bnt);
    f.add(R);
    f.add(bnt);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
});
            panel.setLayout(null);
            panel.add(ouvir);
            panel.add(devolver);
            panel.add(requisitar);
            panel.add(id);
            panel.add(titulo);
            panel.add(categoria);
            panel.add(totalExemplares);
            panel.add(totalDisponiveis);
            panel.add(preco);
            panel.add(dndac);
            panel.add(realizador);
            panel.add(url);

            return panel;
        }
    }

    ///// colocar private neste metodo
    private void addLivro() {
        JFrame f = new JFrame();
        JButton bnt = new JButton("Gravar");

        JLabel tt;
        JLabel lab1, lab2, lab3, lab5, lab6, lab7, lab8;

        JTextField f1, f2, f3, f5, f6, f7, f8;
        JComboBox cats;

        f1 = new JTextField();
        f2 = new JTextField();
        f3 = new JTextField();
        f5 = new JTextField();
        f6 = new JTextField();
        f7 = new JTextField();
        f8 = new JTextField();

        tt = new JLabel("Adicione um Livro");
        lab1 = new JLabel("Digite o título do Livro:");
        lab2 = new JLabel("Quem são os Autores:");
        lab3 = new JLabel("Digite o ISBN: ");
        cats = new JComboBox(arrayOptions.toArray(new String[0]));
        lab5 = new JLabel("Digite o Ano: ");
        lab6 = new JLabel("Digite a quantidade:");
        lab7 = new JLabel("Url Path image: ");
        lab8 = new JLabel("Quanto custa: ");

        /// (String titulo, String categoria, int totalExemplares, double preco, int anoRegistro, String autor, String isbn, String url) {
        ///         super(titulo, categoria, totalExemplares, preco, anoRegistro, url);


        f1.setBounds(240, 70, 200, 25);
        lab1.setBounds(20, 70, 200, 25);

        f2.setBounds(240, 120, 200, 25);
        lab2.setBounds(20, 120, 200, 25);
        f3.setBounds(240, 160, 200, 25);
        lab3.setBounds(20, 160, 200, 25);
        cats.setBounds(20, 200, 200, 25);
        f5.setBounds(240, 240, 200, 25);
        lab5.setBounds(20, 240, 200, 25);
        f6.setBounds(240, 280, 200, 25);
        lab6.setBounds(20, 280, 200, 25);
        f7.setBounds(240, 320, 200, 25);
        lab7.setBounds(20, 320, 200, 25);
        f8.setBounds(240, 360, 200, 25);
        lab8.setBounds(20, 360, 200, 25);
        tt.setBounds(20, 10, 300, 40);
        tt.setFont(new Font("Arial", Font.BOLD, 20));
        bnt.setBounds(300, 400, 100, 25);

        f.add(bnt);
        f.add(tt);
        f.add(f1);
        f.add(f2);
        f.add(f3);
        f.add(f5);
        f.add(f6);
        f.add(f7);
        f.add(f8);

        f.add(lab1);
        f.add(lab2);
        f.add(lab3);
        f.add(cats);
        f.add(lab5);
        f.add(lab6);
        f.add(lab7);
        f.add(lab8);
        ///       lab1 = new JLabel("Digite o título do Livro:");
        ///         lab2 = new JLabel("Quem são os Autores:");
        ///         lab3 = new JLabel("Digite o ISBN: ");
        ///         cats = new JComboBox(arrayOptions.toArray(new String[0]));
        ///         lab5 = new JLabel("Digite o Ano: ");
        ///         lab6 = new JLabel("Digite a quantidade:");
        ///         lab7 = new JLabel("Url Path image: ");
        ///         lab8 = new JLabel("Quanto custa: ");
        ///    f2.setBounds(240, 120, 200, 25);
        ///         lab2.setBounds(20, 120, 200, 25);
        ///         f3.setBounds(240, 160, 200, 25);
        ///         lab3.setBounds(20, 160, 200, 25);
        ///         cats.setBounds(20, 200, 200, 25);
        ///         f5.setBounds(240, 240, 200, 25);
        ///         lab5.setBounds(20, 240, 200, 25);
        ///         f6.setBounds(240, 280, 200, 25);
        ///         lab6.setBounds(20, 280, 200, 25);
        ///         f7.setBounds(240, 320, 200, 25);
        ///         lab7.setBounds(20, 320, 200, 25);
        ///         f8.setBounds(240, 360, 200, 25);
        ///         lab8.setBounds(20, 360, 200, 25);
        ///         tt.setBounds(20, 10, 300, 40);
        ///         tt.setFont(new Font("Arial", Font.BOLD, 20));
        bnt.addActionListener(e -> {

            // Validar campos vazios
            if (f1.getText().trim().isEmpty() ||
                    f2.getText().trim().isEmpty() ||
                    f3.getText().trim().isEmpty() ||
                    f5.getText().trim().isEmpty() ||
                    f6.getText().trim().isEmpty() ||
                    f7.getText().trim().isEmpty() ||
                    f8.getText().trim().isEmpty() ||
                    cats.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null,
                        "Preencha todos os campos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar se os campos numéricos são realmente números
            try {
                int ano = Integer.parseInt(f6.getText());
                int paginas = Integer.parseInt(f8.getText());
                int exemplares = Integer.parseInt(f5.getText());

                // Se chegou aqui, os dados estão correctos
                mediateca.addObra(new Livro(
                        f1.getText(),
                        cats.getSelectedItem().toString(),
                        ano,
                        paginas,
                        exemplares,
                        f2.getText(),
                        f3.getText(),
                        f7.getText()
                ));

                JOptionPane.showMessageDialog(null,
                        "Livro adicionado com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Os campos Ano, Páginas e Exemplares devem conter apenas números!",
                        "Erro de validação",
                        JOptionPane.ERROR_MESSAGE);
            }

        });

        f.getContentPane().setBackground(Color.white);
        f.setSize(500, 500);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private void addDvd() {
        JFrame f = new JFrame();
        JButton bnt = new JButton("Gravar");

        JLabel tt;
        JLabel lab1, lab2, lab3, lab5, lab6, lab7, lab8;

        JTextField f1, f2, f3, f5, f6, f7, f8;
        JComboBox cats;

        f1 = new JTextField();
        f2 = new JTextField();
        f3 = new JTextField();
        f5 = new JTextField();
        f6 = new JTextField();
        f7 = new JTextField();
        f8 = new JTextField();

        tt = new JLabel("Adicione um DVD");
        lab1 = new JLabel("Digite o título do DVD:");
        lab2 = new JLabel("Quem é o Realizador:");
        lab3 = new JLabel("Digite o DNDAC: ");
        cats = new JComboBox(arrayOptions.toArray(new String[0]));
        lab5 = new JLabel("Digite o Ano: ");
        lab6 = new JLabel("Digite a quantidade:");
        lab7 = new JLabel("Url Path image: ");
        lab8 = new JLabel("Quanto custa: ");

        f1.setBounds(240, 70, 200, 25);
        lab1.setBounds(20, 70, 200, 25);

        f2.setBounds(240, 120, 200, 25);
        lab2.setBounds(20, 120, 200, 25);

        f3.setBounds(240, 160, 200, 25);
        lab3.setBounds(20, 160, 200, 25);

        cats.setBounds(20, 200, 200, 25);

        f5.setBounds(240, 240, 200, 25);
        lab5.setBounds(20, 240, 200, 25);

        f6.setBounds(240, 280, 200, 25);
        lab6.setBounds(20, 280, 200, 25);

        f7.setBounds(240, 320, 200, 25);
        lab7.setBounds(20, 320, 200, 25);

        f8.setBounds(240, 360, 200, 25);
        lab8.setBounds(20, 360, 200, 25);

        tt.setBounds(20, 10, 300, 40);
        tt.setFont(new Font("Arial", Font.BOLD, 20));
        bnt.setBounds(300, 400, 100, 25);
        bnt.addActionListener(e -> {

            // Validar campos vazios
            if (f1.getText().trim().isEmpty() ||
                    f2.getText().trim().isEmpty() ||
                    f3.getText().trim().isEmpty() ||
                    f5.getText().trim().isEmpty() ||
                    f6.getText().trim().isEmpty() ||
                    f7.getText().trim().isEmpty() ||
                    f8.getText().trim().isEmpty() ||
                    cats.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null,
                        "Preencha todos os campos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Conversões
                int ano = Integer.parseInt(f5.getText());
                int quantidade = Integer.parseInt(f6.getText());
                double preco = Double.parseDouble(f8.getText());

                // Criar DVD
                mediateca.addObra(new DVD(
                        f1.getText(),                          // título
                        cats.getSelectedItem().toString(),     // categoria
                        quantidade,                            // totalExemplares
                        preco,                                 // preço
                        ano,                                   // ano
                        f2.getText(),                          // realizador
                        f3.getText(),                          // DNDAC
                        f7.getText()                           // URL
                ));

                JOptionPane.showMessageDialog(null,
                        "DVD adicionado com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Ano, Quantidade e Preço devem ser numéricos!",
                        "Erro de validação",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
        f.add(bnt);
        f.add(tt);
        f.add(f1);
        f.add(f2);
        f.add(f3);
        f.add(f5);
        f.add(f6);
        f.add(f7);
        f.add(f8);

        f.add(lab1);
        f.add(lab2);
        f.add(lab3);
        f.add(cats);
        f.add(lab5);
        f.add(lab6);
        f.add(lab7);
        f.add(lab8);

        f.getContentPane().setBackground(Color.white);
        f.setSize(500, 500);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private JFrame frameAbrir(){
        this.dispose();

        JButton bntObaPorID, bntTitulo, AddLivro, AddCategoria, back;

        JLabel IdDeObra = new JLabel("ID da Obra :");
        JLabel porAutor = new JLabel("Titulo / Autor / Realizador :");

        bntObaPorID = new JButton("I");
        bntTitulo = new JButton("T");

        AddLivro = new JButton("Add Livro");
        AddCategoria = new JButton("Add Cat");
        back = new JButton("Voltar");

        JComboBox<String> combo;

        frameAbrir = new JFrame();
        frameAbrir.setLayout(new BorderLayout());

        back.addActionListener(e1 -> {
            frameAbrir.dispose();
            new Frame();
        });

        AddLivro.addActionListener(e1 -> {
            JFrame fr = new JFrame();
            fr.setSize(240, 100);

            JButton livro, dvd;
            livro = new JButton("Add Livro");
            dvd = new JButton("Add DVD");

            fr.setLayout(null);

            livro.setBounds(2, 20, 97, 28);
            dvd.setBounds(110, 20, 97, 28);

            livro.addActionListener(e2 -> {addLivro(); fr.dispose();});
            dvd.addActionListener(e2 -> {addDvd(); fr.dispose();});

            fr.add(dvd);
            fr.add(livro);

            fr.setLocationRelativeTo(null);
            fr.setResizable(false);
            fr.setVisible(true);
        });

        combo = new JComboBox<>(arrayOptions.toArray(new String[0]));

        AddCategoria.addActionListener(e1 -> {
            JFrame fr = new JFrame();
            fr.setLocationRelativeTo(null);

            JButton addC = new JButton("Add");
            fr.setSize(340, 100);

            JTextField addCategoria = new JTextField();
            addCategoria.setBounds(10, 10, 200, 20);
            addC.setBounds(210, 10, 70, 20);

            addC.addActionListener(e2 -> {
                if(!addCategoria.getText().isEmpty()){
                    arrayOptions.add(addCategoria.getText());
                    combo.setModel(new DefaultComboBoxModel<>(arrayOptions.toArray(new String[0])));
                    addCategoria.setText("");
                }
               fr.dispose();
            });

            fr.setLayout(null);
            fr.add(addC);
            fr.add(addCategoria);

            fr.setResizable(false);
            fr.setVisible(true);
        });

        JTextField ObraPorID, ObraPorTituloAutorRealizador;
        ObraPorID = new JTextField();
        ObraPorTituloAutorRealizador = new JTextField();

        bntTitulo.setBounds(550, 35, 33, 22);
        bntObaPorID.setBounds(550, 10, 33, 22);

        JPanel abrirPanelTop = new JPanel();
        combo.setBounds(2, 2, 200, 35);



        JPanel abrirPanelBottom = new JPanel();
        IdDeObra.setBounds(331, 9, 100, 22);
        ObraPorID.setBounds(400, 10, 150, 22);
        porAutor.setBounds(250, 33, 300, 22);
        back.setBounds(0, 38, 80, 27);
        AddCategoria.setBounds(81, 38, 80, 27);
        AddLivro.setBounds(162, 38, 80, 27);
        ObraPorTituloAutorRealizador.setBounds(400, 35, 150, 22);

        abrirPanelTop.setPreferredSize(new Dimension(600, 70));
        abrirPanelBottom.setLayout(new FlowLayout(FlowLayout.LEADING));
        abrirPanelBottom.setPreferredSize(new Dimension(600, 650));
        abrirPanelTop.setLayout(null);

        Obra ds = new DVD("Daniel", "Obras de referência", 20, 2000, 2015, "Daniel Samba", "FFF", "/DVD/dvd.png");
        Obra dn = new Livro("Paulo", "Obras de referência", 30, 34000, 2002, "Regina", "23euvhw", "/Imagens/dic1.png");



        mediateca.addObra(ds);
        mediateca.addObra(dn);


        combo.addActionListener(e1 ->{
            String categoria = combo.getSelectedItem().toString();

            ArrayList<Obra> obras= mediateca.getObraAll();
            abrirPanelBottom.removeAll();
            boolean encontrou = false;
            for(Obra obra: obras){
                if (obra.getCategoria().equals(categoria)){
                    abrirPanelBottom.add(card(obra));

                    encontrou = true;
                }
            }
            abrirPanelBottom.revalidate();
            abrirPanelBottom.repaint();
            if (!encontrou) {
                abrirPanelBottom.add(new JLabel("Nenhuma obra nesta categoria"));
            }
        });

        bntObaPorID.addActionListener(e1 -> {

            ArrayList<Obra> obras = mediateca.getObraAll();
            abrirPanelBottom.removeAll();

            boolean encontrou = false;
            String textoID = ObraPorID.getText().trim();
            abrirPanelBottom.removeAll();
            for (Obra obra : obras) {
                if (obra.getId().contains(textoID)) {
                    abrirPanelBottom.add(card(obra));
                    encontrou = true;
                }
            }

            if (!encontrou) {
                JLabel msg = new JLabel("Nenhuma obra com este ID");
                msg.setHorizontalAlignment(SwingConstants.CENTER);
                abrirPanelBottom.add(msg);
            }

            abrirPanelBottom.revalidate();
            abrirPanelBottom.repaint();
        });


        bntTitulo.addActionListener(e1 -> {

            ArrayList<Obra> obras = mediateca.getObraAll();
            abrirPanelBottom.removeAll();

            boolean encontrou = false;
            String texto = ObraPorTituloAutorRealizador.getText().trim().toLowerCase();

            for (Obra obra : obras) {

                // título
                if (obra.getTitulo().toLowerCase().contains(texto)) {
                    abrirPanelBottom.add(card(obra));
                    encontrou = true;
                    continue;
                }

                // autor (Livro)
                if (obra instanceof Livro) {
                    Livro livro = (Livro) obra;
                    if (livro.getAutor().toLowerCase().contains(texto)) {
                        abrirPanelBottom.add(card(obra));
                        encontrou = true;
                        continue;
                    }
                }

                // realizador (DVD)
                if (obra instanceof DVD) {
                    DVD dvd = (DVD) obra;
                    if (dvd.getRealizador().toLowerCase().contains(texto)) {
                        abrirPanelBottom.add(card(obra));
                        encontrou = true;
                    }
                }
            }

            if (!encontrou) {
                JLabel msg = new JLabel("Nenhuma obra encontrada");
                msg.setHorizontalAlignment(SwingConstants.CENTER);
                abrirPanelBottom.add(msg);
            }

            abrirPanelBottom.revalidate();
            abrirPanelBottom.repaint();

        });

        abrirPanelTop.add(combo);
        abrirPanelTop.add(bntTitulo);
        abrirPanelTop.add(bntObaPorID);
        abrirPanelTop.add(AddCategoria);
        abrirPanelTop.add(AddLivro);
        abrirPanelTop.add(porAutor);
        abrirPanelTop.add(IdDeObra);
        abrirPanelTop.add(ObraPorID);
        abrirPanelTop.add(ObraPorTituloAutorRealizador);
        abrirPanelTop.add(back);

        JScrollPane scroll = new JScrollPane(
                abrirPanelBottom,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        frameAbrir.add(abrirPanelTop, BorderLayout.NORTH);
        frameAbrir.add(scroll, BorderLayout.CENTER);

        frameAbrir.setSize(600, 600);
        frameAbrir.setLocationRelativeTo(null);
        frameAbrir.setVisible(true);
        return frameAbrir;
    }
    private JFrame newFrame(){

        newFrame = new JFrame();
        newFrame.setSize(500, 500);

        JButton b1, b2, b3, b4;
        b1 = new JButton("Registar Utente");
        b2 = new JButton("Mostrar Utentes");
        b3 = new JButton("Mostrar Utente");
        b4 = new JButton("Sair");

        newFrame.setLayout(null);

        b1.setBounds(80, 30, 300, 65);
        b2.setBounds(80, 100, 300, 65);
        b3.setBounds(80, 170, 300, 65);
        b4.setBounds(80, 240, 300, 65);

        newFrame.add(b1);
        newFrame.add(b2);
        newFrame.add(b3);
        newFrame.add(b4);

        b4.addActionListener(ev -> newFrame.dispose());
        b2.addActionListener(e -> {
            showUtente();
        });
        b3.addActionListener(e -> {
           showUtente();
        });
        b1.addActionListener(ev -> {
            newFrame.dispose();
            JFrame frame = new JFrame();
            JButton adds = new JButton("Criar Utente");
            JTextField field = new JTextField();
            JTextField field1 = new JTextField();
            JLabel nome = new JLabel("Nome:");
            JLabel Email = new JLabel("Email");
            nome.setBounds(10,30,100,25);
            Email.setBounds(10,60,100,25);
            field1.setBounds(80,30, 288,25);
            field.setBounds(80,60, 288,25);
            adds.setBounds(50, 100,150, 25 );
            adds.addActionListener(e1 -> {
                if (field.getText().contains("@")){
                   mediateca.addUtente(new Utente(field1.getText(), field.getText()));
                    JOptionPane.showMessageDialog(null, "Utente criado com sucesso");
                    frame.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Utente não criado");
                    frame.dispose();

                }

            });
            frame.add(field1);
            frame.add(Email);
            frame.add(nome);
            frame.add(field);
            frame.add(adds);
            frame.setSize(400, 250);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setVisible(true);
        });


        newFrame.setLocationRelativeTo(null);
        newFrame.setResizable(false);
        newFrame.setVisible(true);
        return newFrame;
    }
    private JFrame showUtente(){
JFrame frame2;
        JButton bntt = new JButton("Back");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel label = new JLabel(mediateca.getAllUtente());

        label.setBounds(0,50,500,440);
        label.setBackground(new Color(200,100,100));
        panel.setBounds(0,40,500,440);
        panel.setBackground(Color.white);
        panel.add(label);

        frame2 = new JFrame();
        frame2.add(panel);
        frame2.setSize(500, 500);
        bntt.setBounds(350,10,120,30);
        frame2.add(bntt);
        bntt.addActionListener(e1 ->{
            frame2.dispose();

        });
        frame2.setResizable(false);
        frame2.setLocationRelativeTo(null);
        frame2.setLayout(null);
        frame2.setVisible(true);

        return frame2;
    }
}
