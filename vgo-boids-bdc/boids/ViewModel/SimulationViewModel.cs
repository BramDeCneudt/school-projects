using System;
using Model;
using Mathematics;
using System.Threading;
using System.Windows;
using System.Windows.Threading;
using System.Windows.Input;
using Model.Species;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using Cells;
using System.Collections.Specialized;

namespace ViewModel
{
    public class SimulationViewModel
    {
        private Simulation Simulation { get; set; }

        public ICommand StartStopTime { get; private set; }
        public ICommand Up { get; private set; }
        public ICommand Down { get; private set; }
        public ICommand Left { get; private set; }
        public ICommand Right { get; private set; }


        public DispatcherTimer Timer { get; set; }
        public WorldViewModel World { get; private set; }
        public List<SpeciesViewModel> Species { get; set; }
        public Cell<SpeciesViewModel> SelectedSpecies { get; set; }
        public SpeciesViewModel ControllerSpecies { get; set; }

        public Cell<Vector2D> Vector { get; set; }

        public SimulationViewModel()
        {
            this.Simulation = new Simulation();
            this.World = new WorldViewModel(Simulation.World);
            this.Species = new List<SpeciesViewModel>();

            Vector = Cell.Create(new Vector2D(50, 50));


            foreach (BoidSpecies item in Simulation.Species)
            {
                Species.Add(new SpeciesViewModel(item, this));
            }

            int i = 10;

            foreach (var item in this.Simulation.Species)
            {
                item.CreateBoid(new Vector2D(i, i));
                i += 100;
            }

            this.SelectedSpecies = Cell.Create(Species[0]);
            this.ControllerSpecies = Species.First(s => s.Name.Equals("controller"));

            StartStopTime = new StartStopTimerCommand(this);


        }

        public double getSpeed()
        {
            return this.ControllerSpecies.Parameters.getParameterVM("Maximum Speed").Value.Value;
        }

        public double setSpeed(double speed)
        {
            return this.ControllerSpecies.Parameters.getParameterVM("Maximum Speed").Value.Value = speed;
        }

        public void goDirectionX(double x)
        {

            this.ControllerSpecies.Parameters.getParameterVM("X").Value.Value = x;
        }

        public void goDirectionY(double y)
        {
            this.ControllerSpecies.Parameters.getParameterVM("Y").Value.Value = y;
        }


        public void update(double timer)
        {
            Simulation.Update(timer);
        }

        //NESTED CLASSES
        private class StartStopTimerCommand : ICommand
        {
            public StartStopTimerCommand(SimulationViewModel vm)
            {
                viewModel = vm;
            }

            private SimulationViewModel viewModel;
            public event EventHandler CanExecuteChanged;

            public bool CanExecute(object parameter)
            {
                return true;
            }

            public void Execute(object parameter)
            {
                if (viewModel.Timer.IsEnabled)
                {
                    viewModel.Timer.Stop();
                }
                else
                {
                    viewModel.Timer.Start();
                }

            }
        }



    }
}

