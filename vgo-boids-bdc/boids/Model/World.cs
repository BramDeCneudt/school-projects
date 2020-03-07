using Bindings;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class World
    {
        public static readonly RangedDoubleParameter Width = new RangedDoubleParameter("Width", 1000, 200, 10000);

        public static readonly RangedDoubleParameter Height = new RangedDoubleParameter("Height", 500, 200, 10000);

        public World()
        {
            this.Population = new ObservableCollection<Boid>();
            this.Bindings = new ParameterBindings("World").Initialize(Width).Initialize(Height);
        }

        public ObservableCollection<Boid> Population { get; }

        double timePassed = 0;

        public void Update(double dt)
        {
            timePassed += dt;
            foreach (var boid in Population)
            {
                boid.Update(dt);
            }
            CheckSize();

            if (timePassed > 2)
            {
                UpdateSize();
                timePassed = 0;
            }



        }

        private void UpdateSize()
        {
            foreach (Boid item in Population)
            {
                switch (item.Species.Name)
                {
                    case "prey":
                        item.MoreSize();
                        item.MoreSize();
                        break;
                    case "hunter":
                        item.LessSize();
                        break;
                    case "controller":
                        item.LessSize();
                        break;
                }

            }


        }

        private void CheckSize()
        {
            //maak nieuw populatie aan om te checken haal daar de eerste uit verwijder die en check of die dicht bij andere ligt
            var testPopulation = new ObservableCollection<Boid>(Population);
            Boid selectedBoid;

            foreach (Boid boid in testPopulation.Reverse())
            {
                selectedBoid = boid;
                testPopulation.Remove(boid);

                if (selectedBoid.Species.Name.Equals("controller") || selectedBoid.Species.Name.Equals("hunter"))
                {
                    foreach (Boid subBoid in testPopulation.Reverse())
                    {
                        if (subBoid.Species.Name.Equals("prey"))
                        {

                        int straal = (boid.Size.Value / 2);
                        var sumX = subBoid.Position.Value.X - selectedBoid.Position.Value.X;
                        var sumY = subBoid.Position.Value.Y - selectedBoid.Position.Value.Y;
                        sumX = Math.Abs(sumX);
                        sumY = Math.Abs(sumX);
                        if ((sumX < straal || sumY < straal))
                        {
                            if (selectedBoid.Size.Value < selectedBoid.maxSize && subBoid.Size.Value > subBoid.minSize)
                            {
                                    if (subBoid.LessSize())
                                    {
                                        selectedBoid.MoreSize();
                                    }
                                
                                
                            }

                        }
                    }
                }

            }

        }

    }

    public ParameterBindings Bindings { get; }
}
}
