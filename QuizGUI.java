import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGUI extends JFrame {
    private int currentQuestion = 0;
    private String[] respostasCorretas = {
        "b", "a", "b", "c", "b", "a", "b", "a", "c", "a", "d", "a", "d", "c", "d",
        "b", "c", "d", "a", "d", "a", "b", "c", "d", "a", "b", "d", "c", "b", "a"
    };
    private String[] perguntas = {
        "Qual é a capital do Brasil?", "Quem escreveu 'Dom Quixote'?",
        "Qual é o maior planeta do Sistema Solar?", "Quem pintou a 'Mona Lisa'?",
        "Qual é o país mais populoso do mundo?", "Quem foi o primeiro presidente dos Estados Unidos?",
        "Qual é o rio mais longo do mundo?", "Quem escreveu 'A Odisséia'?",
        "Qual é o elemento mais abundante na crosta terrestre?", "Quem foi o primeiro homem a pisar na lua?",
        "Qual é a língua mais falada no mundo?", "Quem desenvolveu a teoria da relatividade?",
        "Qual é a montanha mais alta do mundo?", "Qual é o oceano mais profundo?",
        "Qual é a menor unidade de vida?", "Quem foi o descobridor da América?",
        "Qual é o maior deserto do mundo?", "Quem escreveu '1984'?", 
        "Qual é a fórmula química da água?", "Qual é o país mais novo do mundo?",
        "Quem pintou 'A Noite Estrelada'?", "Qual é o órgão mais pesado do corpo humano?",
        "Qual é a moeda do Japão?", "Quem escreveu 'O Senhor dos Anéis'?",
        "Qual é o planeta mais próximo do Sol?", "Quem descobriu a penicilina?",
        "Qual é a cidade mais populosa do mundo?", "Qual é o metal mais abundante na Terra?",
        "Qual é o maior oceano do mundo?", "Quem desenvolveu a teoria da evolução?"
    };
    private String[][] opcoes = {
        {"São Paulo", "Brasília", "Rio de Janeiro", "Salvador"},
        {"Miguel de Cervantes", "William Shakespeare", "Machado de Assis", "J.K. Rowling"},
        {"Terra", "Júpiter", "Saturno", "Marte"},
        {"Pablo Picasso", "Vincent van Gogh", "Leonardo da Vinci", "Claude Monet"},
        {"Índia", "China", "Estados Unidos", "Brasil"},
        {"George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams"},
        {"Rio Amazonas", "Rio Nilo", "Rio Yangtze", "Rio Mississipi"},
        {"Homero", "Virgílio", "Ovídio", "Sófocles"},
        {"Silício", "Ferro", "Oxigênio", "Carbono"},
        {"Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Alan Shepard"},
        {"Inglês", "Espanhol", "Hindi", "Mandarim"},
        {"Albert Einstein", "Isaac Newton", "Galileu Galilei", "Nikola Tesla"},
        {"Monte Everest", "K2", "Kangchenjunga", "Lhotse"},
        {"Atlântico", "Índico", "Pacífico", "Ártico"},
        {"Célula", "Átomo", "Molécula", "Organelos"},
        {"Cristóvão Colombo", "Vasco da Gama", "Ferdinand Magellan", "James Cook"},
        {"Saara", "Gobi", "Kalahari", "Antártica"},
        {"George Orwell", "Aldous Huxley", "Ray Bradbury", "J.K. Rowling"},
        {"H2O", "CO2", "NaCl", "O2"},
        {"Sudão do Sul", "Kosovo", "Timor-Leste", "Montenegro"},
        {"Vincent van Gogh", "Claude Monet", "Leonardo da Vinci", "Edvard Munch"},
        {"Fígado", "Pulmão", "Cérebro", "Coração"},
        {"Yen", "Won", "Baht", "Dólar"},
        {"J.R.R. Tolkien", "J.K. Rowling", "George R.R. Martin", "C.S. Lewis"},
        {"Mercúrio", "Vênus", "Terra", "Marte"},
        {"Alexander Fleming", "Louis Pasteur", "Joseph Lister", "Robert Koch"},
        {"Tóquio", "Nova Iorque", "Xangai", "Mumbai"},
        {"Alumínio", "Ferro", "Cálcio", "Cobre"},
        {"Atlântico", "Índico", "Pacífico", "Ártico"},
        {"Charles Darwin", "Jean-Baptiste Lamarck", "Gregor Mendel", "Louis Pasteur"}
    };
    private String[] dicas = {
        "É uma cidade planejada.", "Autor espanhol famoso.",
        "Conhecido como o gigante gasoso.", "Obra famosa de um artista italiano.",
        "País localizado na Ásia.", "Primeiro presidente dos EUA.",
        "Rio famoso no Egito.", "Autor grego antigo.",
        "Elemento essencial para a vida.", "Primeiro humano a caminhar na Lua.",
        "É falada por mais de 1 bilhão de pessoas.", "Famoso físico alemão.",
        "Localizada no Himalaia.", "Oceano mais extenso.",
        "Fundamental para todos os seres vivos.", "Explorador genovês.",
        "Deserto frio.", "Autor britânico.",
        "É composta por dois elementos.", "Independência em 2011.",
        "Famosa pintura pós-impressionista.", "Responsável por desintoxicar o corpo.",
        "Moeda asiática.", "Famoso escritor de fantasia.",
        "Planeta mais rápido a orbitar o Sol.", "Revolucionou a medicina.",
        "Localizada no Japão.", "Utilizado em construções.",
        "Cobre a maior parte da Terra.", "Naturalista britânico."
    };

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private JButton hintButton;
    private JPanel questionPanel;
    private JPanel optionsPanel;

    public QuizGUI() {
        setTitle("Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionLabel = new JLabel(perguntas[currentQuestion]);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2)); // Alternativas em duas colunas

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton(opcoes[currentQuestion][i]);
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        nextButton = new JButton("Próxima");
        nextButton.addActionListener(new NextButtonListener());

        hintButton = new JButton("Dica");
        hintButton.addActionListener(new HintButtonListener());

        questionPanel = new JPanel(new BorderLayout());
        questionPanel.add(questionLabel, BorderLayout.NORTH);
        questionPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(hintButton);
        buttonPanel.add(nextButton);

        questionPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(questionPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean answered = false;
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    answered = true;
                    if (!optionButtons[i].getText().substring(0, 1).equalsIgnoreCase(respostasCorretas[currentQuestion])) {
                        JOptionPane.showMessageDialog(QuizGUI.this, "Resposta errada! O quiz será reiniciado.");
                        resetQuiz();
                        return;
                    }
                    break;
                }
            }

            if (answered) {
                currentQuestion++;
                if (currentQuestion < perguntas.length) {
                    updateQuestion();
                } else {
                    JOptionPane.showMessageDialog(QuizGUI.this, "Parabéns! Você completou o quiz.");
                    resetQuiz();
                }
            } else {
                JOptionPane.showMessageDialog(QuizGUI.this, "Por favor, selecione uma resposta.");
            }
        }
    }

    private class HintButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(QuizGUI.this, "Dica: " + dicas[currentQuestion]);
        }
    }

    private void updateQuestion() {
        questionLabel.setText(perguntas[currentQuestion]);
        buttonGroup.clearSelection();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(opcoes[currentQuestion][i]);
        }
    }

    private void resetQuiz() {
        currentQuestion = 0;
        updateQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizGUI();
            }
        });
    }
}
