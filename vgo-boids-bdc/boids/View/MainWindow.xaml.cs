using Bindings;
using Mathematics;
using Model;
using Model.Species;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;
using ViewModel;

namespace View
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private SimulationViewModel simulation;
        private bool up, down, left, right, space;

        public MainWindow(SimulationViewModel sim)
        {
            InitializeComponent();
            this.DataContext = sim;
            this.simulation = sim;
            Start();
        }

        private void Start()
        {
            simulation.Timer = new DispatcherTimer(TimeSpan.FromMilliseconds(20), DispatcherPriority.Render, (x, y) => { simulation.update(0.02); tick(); }, this.Dispatcher);
            simulation.Timer.Start();
        }


        private void boidsWindow_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Z)
            {
                this.up = e.IsDown;
            }
            if (e.Key == Key.S)
            {
                this.down = e.IsDown;
            }
            if (e.Key == Key.Q)
            {
                this.left = e.IsDown;
            }
            if (e.Key == Key.D)
            {
                this.right = e.IsDown;
            }
            if (e.Key == Key.Space)
            {
                this.space = e.IsDown;
            }


        }

        private void boidsWindow_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Z)
            {
                this.up = e.IsDown;
            }
            if (e.Key == Key.S)
            {
                this.down = e.IsDown;
            }
            if (e.Key == Key.Q)
            {
                this.left = e.IsDown;
            }
            if (e.Key == Key.D)
            {
                this.right = e.IsDown;
            }
            if (e.Key == Key.Space)
            {
                this.space = e.IsDown;
            }
        }

        private void WrapPanel_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            Point point = e.GetPosition(this);
            Vector2D vec = new Vector2D(point.X, point.Y);
            simulation.Vector.Value = vec;
        }


        private void tick()

        {
            if (up)
            {
                simulation.setSpeed(2000);
                simulation.goDirectionY(-1000);
            }
            if (down)
            {
                simulation.setSpeed(2000);
                simulation.goDirectionY(+1000);
            }
            if (left)
            {
                simulation.setSpeed(2000);
                simulation.goDirectionX(-1000);
            }
            if (right)
            {
                simulation.setSpeed(2000);
                simulation.goDirectionX(1000);
            }

            if (space)
            {
                simulation.setSpeed(0);
            }

            if (!up && !down && !left && !right && simulation.getSpeed() > 0) {
                simulation.setSpeed(0);
                simulation.goDirectionX(0);
                simulation.goDirectionY(0);
            }
        }
    }
}
