import React, { useState } from "react";
import * as Select from "@radix-ui/react-select";
import classnames from "classnames";
import {
  CheckIcon,
  ChevronDownIcon,
  ChevronUpIcon,
} from "@radix-ui/react-icons";

interface SelectProps {
  onValueChange: (value: string) => void;
  type: string;
}

export default function SelectDemo({ onValueChange, type }: SelectProps) {
  const [value, setValue] = useState(type);
  console.log(type)

  function handleChange(selectedValue: string) {
    setValue(selectedValue);
    onValueChange(selectedValue);
    console.log(selectedValue);
  }
  return (
    <Select.Root onValueChange={handleChange}>
      <Select.Trigger className="w-full inline-flex items-center justify-center rounded px-[15px] text-[13px] leading-none h-[35px] gap-[5px] bg-white text-violet11 shadow-[0_2px_10px] shadow-black/10 hover:bg-mauve3 focus:shadow-[0_0_0_2px] focus:shadow-black data-[placeholder]:text-violet9 outline-none">
        <Select.Value>
          {value || "Select a type"}
        </Select.Value>
        <Select.Icon className="text-violet11">
          <ChevronDownIcon />
        </Select.Icon>
      </Select.Trigger>
      <Select.Portal>
        <Select.Content className="overflow-hidden bg-white rounded-md shadow-[0px_10px_38px_-10px_rgba(22,_23,_24,_0.35),0px_10px_20px_-15px_rgba(22,_23,_24,_0.2)]">
          <Select.ScrollUpButton className="flex items-center justify-center h-[25px] bg-white text-violet11 cursor-default">
            <ChevronUpIcon />
          </Select.ScrollUpButton>
          <Select.Viewport className="p-[5px]">
            <Select.Group>
              <Select.Label className="px-[25px] text-xs leading-[25px] text-mauve11">
                Valves
              </Select.Label>
              <SelectItem value="Mixproof">Mixproof</SelectItem>
              <SelectItem value="Solenoide">Solenoide</SelectItem>
            </Select.Group>

            <Select.Separator className="h-[1px] bg-violet6 m-[5px]" />

            <Select.Group>
              <Select.Label className="px-[25px] text-xs leading-[25px] text-mauve11">
                Other nodes
              </Select.Label>
              <SelectItem value="Worth">Worth</SelectItem>
              <SelectItem value="Filtration">Filtration</SelectItem>
              <SelectItem value="GreenBeer">GreenBeer</SelectItem>
            </Select.Group>
          </Select.Viewport>
          <Select.ScrollDownButton className="flex items-center justify-center h-[25px] bg-white text-violet11 cursor-default">
            <ChevronDownIcon />
          </Select.ScrollDownButton>
        </Select.Content>
      </Select.Portal>
    </Select.Root>
  );
}

const SelectItem = React.forwardRef(
  ({ children, className, ...props }, forwardedRef) => {
    return (
      <Select.Item
        className={classnames(
          "text-[13px] leading-none text-violet11 rounded-[3px] flex items-center h-[25px] pr-[35px] pl-[25px] relative select-none data-[disabled]:text-mauve8 data-[disabled]:pointer-events-none data-[highlighted]:outline-none data-[highlighted]:bg-violet9 data-[highlighted]:text-violet1",
          className
        )}
        {...props}
        ref={forwardedRef}
      >
        <Select.ItemText>{children}</Select.ItemText>
        <Select.ItemIndicator className="absolute left-0 w-[25px] inline-flex items-center justify-center">
          <CheckIcon />
        </Select.ItemIndicator>
      </Select.Item>
    );
  }
);
