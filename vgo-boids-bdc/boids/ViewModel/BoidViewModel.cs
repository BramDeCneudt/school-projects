using Cells;
using Mathematics;
using Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ViewModel
{
    public class BoidViewModel
    {
        public BoidViewModel(Boid boid)
        {
            Boid = boid;
            this.Parameters = new ParametersViewModel(boid.Bindings);
        }

        public Boid Boid { get; set; }

        public Cell<Vector2D> Position { get { return Boid.Position; } }
        public string Species { get { return Boid.Species.Name; } }
        public ParametersViewModel Parameters { get; set; }
        public Cell<int> Size { get { return Boid.Size; } }

    }

}
