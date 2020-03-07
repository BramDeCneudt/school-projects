using Cells;
using Mathematics;
using Model.Species;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using System.Windows.Threading;

namespace ViewModel
{
   public class SpeciesViewModel
    {
        private SimulationViewModel sim;
        private BoidSpecies Species { get; set; }
        private DispatcherTimer Timer { get { return sim.Timer; } }

        public string Name { get { return Species.Name; } }
        public ICommand AddSpecies { get; private set; } 
        public Vector2D Vector { get { return sim.Vector.Value; } }
        public ParametersViewModel Parameters { get; set; }

        public SpeciesViewModel(BoidSpecies species, SimulationViewModel parent)
        {
            this.sim = parent;
            this.Species = species;
            this.AddSpecies = new AddSpeciesCommand(this);
            Parameters = new ParametersViewModel(species.Bindings);
        }

        public void CreateBoid()
        {
            Species.CreateBoid(Vector);
        }


        //NESTED CLASSES
        private class AddSpeciesCommand : ICommand
        {
            SpeciesViewModel viewModel;
            public AddSpeciesCommand(SpeciesViewModel viewModel)
            {
                this.viewModel = viewModel;
            }

            public event EventHandler CanExecuteChanged;

            public bool CanExecute(object parameter)
            {
                return viewModel.Timer.IsEnabled;
            }

            public void Execute(object parameter)
            {
                viewModel.CreateBoid();
            }
        }


    }
}
