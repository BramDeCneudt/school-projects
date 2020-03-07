using Bindings;
using Cells;
using Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ViewModel
{
    public class WorldViewModel
    {
        private World World { get; set; }
        public ObservableCollection<BoidViewModel> Population { get; set; }
        public Cell<Double> Height { get { return World.Bindings.Read(World.Height); } }
        public Cell<Double> Width { get { return World.Bindings.Read(World.Width); } }

        public ParametersViewModel Parameters { get; set; }

        public WorldViewModel(World world)
        { 
            World = world;
            this.Population = new ObservableCollection<BoidViewModel>(this.World.Population.Select(boid => new BoidViewModel(boid) ));
            this.World.Population.CollectionChanged += HandleChange;
            this.Parameters = new ParametersViewModel(this.World.Bindings);


        }


        private void HandleChange(object sender, NotifyCollectionChangedEventArgs e)
        {
            foreach (var item in e.NewItems)
            {
                var boid = (Boid)item;
                var boidVM = new BoidViewModel(boid);
                Population.Add(boidVM);
            }
        }

        public void addBoid(Boid boid)
        {
            World.Population.Add(boid);
        }




    }
}
